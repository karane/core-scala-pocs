ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "org.karane",
    version := "1.0",
    libraryDependencies ++= Seq(
      "com.github.scopt" %% "scopt" % "4.1.0"
    )
  )
