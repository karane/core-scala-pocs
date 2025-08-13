ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "http4s-poc",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-dsl"          % "0.23.28",
      "org.http4s" %% "http4s-ember-server" % "0.23.28",
      "org.http4s" %% "http4s-ember-client" % "0.23.28",
      "org.http4s" %% "http4s-circe"        % "0.23.28",
      "io.circe"   %% "circe-generic"       % "0.14.9",
      "io.circe"   %% "circe-parser"        % "0.14.9"
    )
  )
