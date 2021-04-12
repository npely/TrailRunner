import sbt.Keys.libraryDependencies

name := "TrailRunner"

organization := "de.htwg.se"

version := "0.1"

scalaVersion := "2.13.3"

val commonDependencies = Seq(
"org.scalactic" %% "scalactic" % "3.0.8",
"org.scalatest" %% "scalatest" % "3.0.8" % "test",
"org.scala-lang.modules" %% "scala-swing" % "2.1.1",
"net.codingwell" %% "scala-guice" % "4.2.6",
"com.google.inject" % "guice" % "4.1.0",
"com.typesafe.play" %% "play-json" % "2.8.1",
"org.scala-lang.modules" %% "scala-xml" % "1.2.0"
)

parallelExecution in Test := false
coverageExcludedPackages := "<empty>;.*aview.*;.*TrailRunner"
coverageEnabled.in(Test, test) := true

lazy val model = (project in file("Model"))
lazy val persistence = (project in file("Persistence")).dependsOn(model).aggregate(model)
lazy val trailRunnerBase = (project in file(".")).dependsOn(persistence).aggregate(persistence).settings(
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