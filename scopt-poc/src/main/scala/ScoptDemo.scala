package org.karane

import scopt.{OParser, Read}

import java.io.File

// Custom enum-like type for log level
sealed trait LogLevel
object LogLevel {
  case object DEBUG extends LogLevel
  case object INFO extends LogLevel
  case object WARN extends LogLevel
  case object ERROR extends LogLevel

  implicit val logLevelRead: Read[LogLevel] = Read.reads {
    case "debug" => DEBUG
    case "info"  => INFO
    case "warn"  => WARN
    case "error" => ERROR
    case other   => throw new IllegalArgumentException(s"Invalid log level: $other")
  }
}

// Base config
case class Config(
                   command: Option[Command] = None,
                   configFile: Option[File] = None
                 )

// Commands for subcommands
sealed trait Command
case class ProcessConfig(
                          inputFiles: Seq[File] = Seq.empty,
                          retries: Int = 3,
                          verbose: Boolean = false,
                          logLevel: LogLevel = LogLevel.INFO,
                          includes: Seq[String] = Seq.empty,
                          output: Option[File] = None
                        ) extends Command

case class AnalyzeConfig(
                          directory: File = new File("."),
                          depth: Int = 1
                        ) extends Command

object ScoptAdvancedDemo {

  def main(args: Array[String]): Unit = {
    val builder = OParser.builder[Config]

    val parser = {
      import builder._
      OParser.sequence(
        programName("scopt-advanced-demo"),
        head("scopt-advanced-demo", "1.0"),

        opt[File]('c', "config")
          .optional()
          .valueName("<file>")
          .action((f, c) => c.copy(configFile = Some(f)))
          .text("Optional config file to load defaults from"),

        help("help").text("Prints this usage text"),

        cmd("process")
          .action((_, c) => c.copy(command = Some(ProcessConfig())))
          .text("Process files")
          .children(
            opt[File]('i', "input")
              .unbounded() // let input appear multiple times
              .required()
              .valueName("<file>")
              .action { (f, c) =>
                c.command match {
                  case Some(pc: ProcessConfig) => c.copy(command = Some(pc.copy(inputFiles = pc.inputFiles :+ f)))
                  case _ => c
                }
              }
              .text("Input files to process (repeatable)"),

            opt[Int]('r', "retries")
              .action {
                case (r, c) =>
                  c.command match {
                    case Some(pc: ProcessConfig) => c.copy(command = Some(pc.copy(retries = r)))
                    case _ => c
                  }
              }
              .validate(r =>
                if (r >= 0) success else failure("Retries must be >= 0")
              )
              .text("Number of retries (default 3)"),

            opt[Unit]('v', "verbose")
              .action {
                case (_, c) =>
                  c.command match {
                    case Some(pc: ProcessConfig) => c.copy(command = Some(pc.copy(verbose = true)))
                    case _ => c
                  }
              }
              .text("Verbose output"),

            opt[LogLevel]("log-level")
              .action {
                case (lvl, c) =>
                  c.command match {
                    case Some(pc: ProcessConfig) => c.copy(command = Some(pc.copy(logLevel = lvl)))
                    case _ => c
                  }
              }
              .text("Log level: debug, info, warn, error (default info)"),

            opt[String]("include")
              .unbounded()
              .action {
                case (inc, c) =>
                  c.command match {
                    case Some(pc: ProcessConfig) => c.copy(command = Some(pc.copy(includes = pc.includes :+ inc)))
                    case _ => c
                  }
              }
              .text("Include paths (repeatable)"),

            opt[File]('o', "output")
              .optional()
              .valueName("<file>")
              .action {
                case (f, c) =>
                  c.command match {
                    case Some(pc: ProcessConfig) => c.copy(command = Some(pc.copy(output = Some(f))))
                    case _ => c
                  }
              }
              .text("Optional output file"),

            checkConfig {
              case Config(Some(pc: ProcessConfig), _) =>
                if (pc.inputFiles.nonEmpty) success
                else failure("At least one input file must be specified")
              case _ => success
            }
          ),

        cmd("analyze")
          .action((_, c) => c.copy(command = Some(AnalyzeConfig())))
          .text("Analyze a directory")
          .children(
            arg[File]("<directory>")
              .optional()
              .action {
                case (dir, c) =>
                  c.command match {
                    case Some(ac: AnalyzeConfig) => c.copy(command = Some(ac.copy(directory = dir)))
                    case _ => c
                  }
              }
              .text("Directory to analyze, default is current directory"),

            opt[Int]('d', "depth")
              .action {
                case (depth, c) =>
                  c.command match {
                    case Some(ac: AnalyzeConfig) => c.copy(command = Some(ac.copy(depth = depth)))
                    case _ => c
                  }
              }
              .validate(d =>
                if (d >= 1) success else failure("Depth must be >= 1")
              )
              .text("Depth of directory traversal (default 1)")
          )
      )
    }

    // Custom error handling: show help on failure
    OParser.parse(parser, args, Config()) match {
      case Some(config) =>
        config.configFile.foreach { file =>
          println(s"Would load config defaults from file: ${file.getAbsolutePath}")
        }

        config.command match {
          case Some(ProcessConfig(inputs, retries, verbose, logLevel, includes, output)) =>
            println(s"Running 'process' command")
            println(s"Input files: ${inputs.mkString(", ")}")
            println(s"Retries: $retries")
            println(s"Verbose: $verbose")
            println(s"Log level: $logLevel")
            println(s"Includes: ${includes.mkString(", ")}")
            println(s"Output file: ${output.map(_.getAbsolutePath).getOrElse("none")}")
          // Implement processing logic here

          case Some(AnalyzeConfig(dir, depth)) =>
            println(s"Running 'analyze' command")
            println(s"Directory: ${dir.getAbsolutePath}")
            println(s"Depth: $depth")
          // Implement analysis logic here

          case None =>
            println("No command specified. Use --help for usage.")
        }

      case _ =>
    }
  }
}
