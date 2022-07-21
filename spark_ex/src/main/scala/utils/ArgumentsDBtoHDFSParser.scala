package utils

object ArgumentsDBtoHDFSParser {

  case class Arguments(appMaster: String = "", jdbcUrl: String = "", dbTable: String = "", hdfsPath: String = "", driverClass: String = "", queryStm: String = "", numPartitions: String = "")

  val parser = new scopt.OptionParser[Arguments]("Parsing application") {
    opt[String]("app-master").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(appMaster = value))

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

    opt[String]("query-stm").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(queryStm = value))

    opt[String]("num-partitions").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(numPartitions = value))
  }


  def getArguments(args: Array[String]): scala.collection.mutable.Map[String, String] = {
    val arguments: scala.collection.mutable.Map[String, String] = collection.mutable.HashMap.empty[String, String]


    var jdbcUrl: String = null
    var appMaster: String = null
    var dbTable: String = null
    var hdfsPath: String = null
    var driverClass: String = null
    var queryStm: String = null
    var numPartitions: String = null

    parser.parse(args, Arguments()) match {
      case Some(arguments) =>
        appMaster = arguments.appMaster
        jdbcUrl = arguments.jdbcUrl
        dbTable = arguments.dbTable
        hdfsPath = arguments.hdfsPath
        driverClass = arguments.driverClass
        queryStm = arguments.queryStm
        numPartitions = arguments.numPartitions
      case None =>
        appMaster = args.apply(0)
        jdbcUrl = args.apply(1)
        dbTable = args.apply(2)
        hdfsPath = args.apply(3)
        driverClass = args.apply(4)
        queryStm = args.apply(5)
        numPartitions = args.apply(6)
    }

    arguments += ("appMaster" -> appMaster)
    arguments += ("jdbcUrl" -> jdbcUrl)
    arguments += ("dbTable" -> dbTable)
    arguments += ("hdfsPath" -> hdfsPath)
    arguments += ("driverClass" -> driverClass)
    arguments += ("queryStm" -> queryStm)
    arguments += ("numPartitions" -> numPartitions)

    return arguments
  }
}