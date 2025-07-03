ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "scala-test-poc",
    idePackagePrefix := Some("org.karane"),
    
    libraryDependencies ++= Seq(
      "org.scalatestplus" %% "mockito-5-10" % "3.2.18.0" % Test,
      "org.scalatest" %% "scalatest" % "3.2.18" % Test
    ),

    testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
  )
