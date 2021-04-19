val commonDependencies = Seq(
  "org.scalactic" %% "scalactic" % "3.0.8",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test",
  "org.scala-lang.modules" %% "scala-swing" % "2.1.1",
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "com.google.inject" % "guice" % "4.1.0",
  "com.typesafe.play" %% "play-json" % "2.8.1",
  "org.scala-lang.modules" %% "scala-xml" % "1.2.0"
)

lazy val model = ProjectRef(uri("https://github.com/npely/TrailRunner#rest"), "model")
lazy val persistence = ProjectRef(uri("https://github.com/npely/TrailRunner#rest"), "persistence")
lazy val controller = (project in file(".")).dependsOn(model, persistence).aggregate(model, persistence).settings(
  name          := "TrailRunner-Controller",
  organization  := "de.htwg.se",
  version       := "0.1",
  scalaVersion  := "2.13.3",
  libraryDependencies ++= commonDependencies,
)
