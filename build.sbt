name := "sweetmelons"

version := "1.0"

lazy val `sweetmelons` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.2"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.slick" %% "slick" % "2.1.0" withJavadoc() withSources(),
  "com.typesafe.play" %% "play-slick" % "0.8.0" withJavadoc() withSources(),
  "com.typesafe.slick" % "slick_2.11" % "2.1.0",
  "com.github.tototoshi" %% "slick-joda-mapper" % "1.2.0",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  // Logging
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "ch.qos.logback" % "logback-classic" % "1.0.13",
  "ch.qos.logback" % "logback-core" % "1.0.13",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.5",
  // Auth
  "jp.t2v" %% "play2-auth" % "0.12.0" withSources() withJavadoc(),
  "jp.t2v" %% "play2-auth-test" % "0.12.0" % "test" withSources() withJavadoc(),
  "jp.t2v" %% "stackable-controller" % "0.4.0" withSources() withJavadoc(),
  // WebJars
  "org.webjars" % "jquery" % "2.1.0-2",
  "org.webjars" % "font-awesome" % "4.1.0",
  "org.webjars" % "bootstrap" % "3.3.1",
  "org.webjars" % "angularjs" % "1.3.8",
  "org.webjars" % "angular-ui-bootstrap" % "0.12.0",
  "org.webjars" % "ng-grid" % "2.0.11-2",
  "org.webjars" % "angular-ui-sortable" % "0.12.7",
  "org.webjars" % "angular-growl" % "0.4.0",
  "org.mockito" % "mockito-core" % "1.10.17" % "test",
  "org.webjars.bower" % "roboto-fontface" % "0.3.0",
  "org.webjars.bower" % "angular-animate" % "1.3.15",
  "org.webjars" % "angular-strap" % "2.2.1",
  "org.webjars" % "angular-ui-router" % "0.2.0"
)

MochaKeys.requires += "./Setup"

pipelineStages := Seq(rjs)

