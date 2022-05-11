package com.tranlam
import org.apache.avro.Schema
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.avro.generic.GenericRecord
import org.apache.spark.{io, _}
import org.apache.spark.streaming._
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions.{col, from_json}
import utils.{DataTypeUtils, DatabaseUtils, DeserUtils}
import utils.ArgumentsParserUtils.getArguments

import scala.collection.JavaConverters._
/**
 * @author ${user.name}
 */
object App {
  def main(args : Array[String]) {
    val arguments = getArguments(args);
    val jdbcUrl = arguments.get("jdbcUrl").get
    val appName = arguments.get("appName").get
    val dbUtils = new DatabaseUtils(jdbcUrl)
    val spark_laucher_config = dbUtils.getSparkLauncherConfig(appName)
    val spark_ingest_config = dbUtils.readSparkIngestConfig(appName)
    val laucher_properties = spark_laucher_config.getProperties
    val topics = spark_ingest_config.asScala.values.toList.sortBy(_.getOrder).map(_.getTopic)

    // config kafka params
    var kafkaParams = Map[String, Object](
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[KafkaAvroDeserializer]
    )
    laucher_properties.asScala.foreach(k => {
      if(k._1.startsWith("_kafka_.")){
        kafkaParams = kafkaParams + (k._1.substring(8) -> k._2)
      }
    })
    kafkaParams = kafkaParams + ("enable.auto.commit" -> (false: java.lang.Boolean))

    // config spark
    val sparkConf = new SparkConf()
      .setMaster(laucher_properties.get("master"))
      .setAppName(appName)
    laucher_properties.asScala.foreach(k => {
      if(k._1.startsWith("spark.")){
        sparkConf.set(k._1, k._2)
      }
    })
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()
    val sc = spark.sparkContext
    val streamingContext = new StreamingContext(sc, Seconds(laucher_properties.get("duration").toInt))
    val sqlContext = spark.sqlContext
    sqlContext.setConf("spark.sql.shuffle.partitions", laucher_properties.get("spark.sql.shuffle.partitions"))

    import spark.implicits._

    topics.foreach(topic => {
      val spark_ingest_config_per_topic = spark_ingest_config.get(topic)
      val topicArr = Array(topic)
      val stream = KafkaUtils.createDirectStream[String, String](
        streamingContext,
        PreferConsistent,
        Subscribe[String, String](topicArr, kafkaParams)
      )

      var batch_counter = 1L
      stream
        .transform { data =>
          batch_counter = batch_counter + 1
          data
        }
        .foreachRDD { rdd =>
          val offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
          var rcs = 0L
          offsetRanges.foreach(offset => {
            rcs += offset.untilOffset - offset.fromOffset
          })
          println(s"this is rcs $rcs")

          if (rcs > 0) {
            val avros = rdd.map(rec => {
                val genericRecord = rec.value().asInstanceOf[GenericRecord]
                (rec.timestamp(), rec.key(), rec.partition(), rec.offset(), genericRecord.toString)
              })
            val schemaString = DeserUtils.getSchemaMessage(laucher_properties.get("_kafka_.schema.registry.url"), topic)
            val schema = new Schema.Parser().parse(schemaString)
            val schemaType = DataTypeUtils.toSqlTypeHelper(schema, Set.empty)
            val dataType = schemaType.dataType

            val df = avros.toDF("_timestamp", "_key", "_partition", "_offset", "_msg")
              .withColumn("_msg", from_json(col("_msg"), dataType))
              .select("_msg.*", "_timestamp", "_key", "_partition", "_offset")
            df.printSchema()
//            df.show(10, false)
            df.createOrReplaceTempView(spark_ingest_config_per_topic.getTempViewFirst)
            val dfFromKafka = sqlContext.sql(spark_ingest_config_per_topic.getSqlParser)
            dfFromKafka.printSchema()
            dfFromKafka.show(10, false)
            dfFromKafka.write
              .format("jdbc")
              .mode(SaveMode.Append)
              .option("url", jdbcUrl)
              .option("driver", "org.postgresql.Driver")
              .option("dbtable", spark_ingest_config_per_topic.getTableDest)
              .save()
          }
          stream.asInstanceOf[CanCommitOffsets].commitAsync(offsetRanges)
          println(s"this is batch_counter: $batch_counter")
        }
    })

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
