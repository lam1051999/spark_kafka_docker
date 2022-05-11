package utils

object ArgumentsParserUtils {

  case class Arguments(appName: String = "",
                       jdbcUrl: String = "")

  val parser = new scopt.OptionParser[Arguments]("Parsing application") {
    opt[String]("app-name").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(appName = value))

    opt[String]("jdbc-url").required()
      .valueName("")
      .action((value, arguments) => arguments.copy(jdbcUrl = value))
  }


  def getArguments(args: Array[String]): scala.collection.mutable.Map[String, String] = {
    val arguments: scala.collection.mutable.Map[String, String] = collection.mutable.HashMap.empty[String, String]


    var jdbcUrl: String = null
    var appName: String = null

    parser.parse(args, Arguments()) match {
      case Some(arguments) =>
        appName = arguments.appName
        jdbcUrl = arguments.jdbcUrl
      case None =>
        appName = args.apply(0)
        jdbcUrl = args.apply(1)
    }

    arguments += ("appName" -> appName)
    arguments += ("jdbcUrl" -> jdbcUrl)

    return arguments
  }
}