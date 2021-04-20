val commonDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scala-lang.modules" %% "scala-swing" % "2.1.1",
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "com.google.inject" % "guice" % "4.1.0",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0"
)

lazy val level = ProjectRef(uri("https://github.com/npely/TrailRunner.git#rest"), "level")
lazy val persistence = (project in file(".")).dependsOn(level).aggregate(level).settings(
  name          := "TrailRunner-Persistence",
  organization  := "de.htwg.se",
  version       := "0.1",
  scalaVersion  := "2.13.3",
  libraryDependencies ++= commonDependencies,
)