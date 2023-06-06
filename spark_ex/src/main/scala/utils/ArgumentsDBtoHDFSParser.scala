package utils

object ArgumentsDBtoHDFSParser {

  case class Arguments(jdbcUrl: String = "",
                       dbTable: String = "",
                       hdfsPath: String = "",
                       driverClass: String = "",
                       numPartitions: String = "",
                       partitionColumn: String = "",
                       query: String = "",
                       lowerBound: String = "",
                       upperBound: String = "")

  val parser = new scopt.OptionParser[Arguments]("Parsing application") {
    opt[String]("jdbc-url").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(jdbcUrl = value))

    opt[String]("db-table").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(dbTable = value))

    opt[String]("hdfs-path").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(hdfsPath = value))

    opt[String]("driver-class").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(driverClass = value))

    opt[String]("num-partitions").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(numPartitions = value))

    opt[String]("partition-column").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(partitionColumn = value))

    opt[String]("query").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(query = value))

    opt[String]("lower-bound").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(lowerBound = value))

    opt[String]("upper-bound").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(upperBound = value))
  }


  def getArguments(args: Array[String]): scala.collection.mutable.Map[String, String] = {
    val arguments: scala.collection.mutable.Map[String, String] = collection.mutable.HashMap.empty[String, String]

    var jdbcUrl: String = null
    var dbTable: String = null
    var hdfsPath: String = null
    var driverClass: String = null
    var numPartitions: String = null
    var partitionColumn: String = null
    var query: String = null
    var lowerBound: String = null
    var upperBound: String = null

    parser.parse(args, Arguments()) match {
      case Some(arguments) =>
        jdbcUrl = arguments.jdbcUrl
        dbTable = arguments.dbTable
        hdfsPath = arguments.hdfsPath
        driverClass = arguments.driverClass
        numPartitions = arguments.numPartitions
        partitionColumn = arguments.partitionColumn
        query = arguments.query
        lowerBound = arguments.lowerBound
        upperBound = arguments.upperBound
    }

    arguments += ("jdbcUrl" -> jdbcUrl)
    arguments += ("dbTable" -> dbTable)
    arguments += ("hdfsPath" -> hdfsPath)
    arguments += ("driverClass" -> driverClass)
    arguments += ("numPartitions" -> numPartitions)
    arguments += ("partitionColumn" -> partitionColumn)
    arguments += ("query" -> query)
    arguments += ("lowerBound" -> lowerBound)
    arguments += ("upperBound" -> upperBound)

    arguments
  }
}