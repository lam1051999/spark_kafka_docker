package com.tranlam
import org.apache.spark.sql.{SaveMode, SparkSession}
import utils.ArgumentsDBtoHDFSParser.getArguments

object DBtoHDFS {
  def main(args : Array[String]): Unit = {
    val arguments = getArguments(args)
    val appMaster = arguments.get("appMaster").get
    val jdbcUrl = arguments.get("jdbcUrl").get
    val dbTable = arguments.get("dbTable").get
    val hdfsPath = arguments.get("hdfsPath").get
    val driverClass = arguments.get("driverClass").get
    val queryStm = arguments.get("queryStm").get
    val numPartitions = arguments.get("numPartitions").get

    val APPLICATION_NAME = "db_to_hdfs_snapshot"

    val sparkSession = SparkSession.builder()
      .master(appMaster)
      .appName(APPLICATION_NAME)
      .getOrCreate()

    val sc = sparkSession.sparkContext
    val sqlC = sparkSession.sqlContext

    val sqlDF = sqlC.read.format("jdbc")
      .option("driver", driverClass)
      .option("url", jdbcUrl)
      .option("dbtable", dbTable)
      .load()

//    sqlDF.createOrReplaceTempView("tempViewForSnapshot")
//    val sqlDFView = sqlC.sql(queryStm)

//    sqlDF.show(10, false)
    sqlDF.write.mode(SaveMode.Overwrite).parquet(hdfsPath)
  }
}
