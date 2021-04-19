import sbt.Keys.libraryDependencies

name := "TrailRunner"
organization := "de.htwg.se"
version := "0.1"
scalaVersion := "2.13.3"

val AkkaVersion = "2.6.8"
val AkkaHttpVersion = "10.2.4"
val commonDependencies = Seq(
"org.scalatest" %% "scalatest" % "3.0.8",
"org.scalatest" %% "scalatest" % "3.0.8" % "test",
"org.scala-lang.modules" %% "scala-swing" % "2.1.1",
"net.codingwell" %% "scala-guice" % "4.2.6",
"com.typesafe.play" %% "play-json" % "2.8.1",
"org.scala-lang.modules" %% "scala-xml" % "1.2.0",
"com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
"com.typesafe.akka" %% "akka-stream" % AkkaVersion,
"com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
"com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion
)

parallelExecution in Test := false
coverageExcludedPackages := "<empty>;.*aview.*;.*TrailRunner"
coverageEnabled.in(Test, test) := true

lazy val model = project in file("Model")
lazy val controller = project in file("Controller")
lazy val persistence = project in file("Persistence")
lazy val view = project in file("View")
lazy val trailRunnerBase = (project in file(".")).dependsOn(view, controller).aggregate(view, controller).settings(
  name := "TrailRunner",
  libraryDependencies ++= commonDependencies,
  assemblyMergeStrategy in assembly := {
    case PathList("reference.conf") => MergeStrategy.concat
    case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    case x => MergeStrategy.first
  },
  assemblyJarName in assembly := "TrailRunner.jar",
  mainClass in assembly := Some("main.TrailRunner")
)