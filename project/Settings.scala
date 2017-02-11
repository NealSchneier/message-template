import sbt.Keys._
import sbt._

object Settings {
  private lazy val versions = new {
    val scalaLogging = "3.5.0"
    val scopt = "3.5.0"
    val specs2 = "3.8.7"
    val scalaTest = "2.2.4"
    val scalaz = "0.8.6"
    val jodaConvert = "1.8"
    val gatling = "2.2.3"
    val jline = "0.9.94"
    val jacksonAnnotations = "2.5.0"
    val typesafeConfig = "1.2.1"
    val ficus = "1.1.1"
    val finagle = "6.31.0"
  }

  private lazy val orgs = new {
    val scalaLogging = "com.typesafe.scala-logging"
    val joda = "org.joda"
    val scopt = "com.github.scopt"
    val specs2 = "org.specs2"
    val scalaz = "org.scalaz.stream"
    val holdenkarau = "com.holdenkarau"
    val gatling = "io.gatling"
    val gatlingCharts: String = gatling + ".highcharts"
    val jline = "jline"
    val fasterxmlJackson = "com.fasterxml.jackson.core"
    val twitter = "com.twitter"
  }

  private lazy val scope = new {
    val provided = "provided"
    val test = "test"
    val it = "it"
    val itAndTest: String = test + "," + it
  }


  lazy val gatlingDeps = Seq(
    orgs.gatlingCharts % "gatling-charts-highcharts" % versions.gatling % scope.itAndTest,
    orgs.gatling % "gatling-test-framework" % versions.gatling % scope.itAndTest
  )

  lazy val configDeps = Seq(
    "com.typesafe" % "config" % versions.typesafeConfig,
    "net.ceedubs" %% "ficus" % versions.ficus excludeAll ExclusionRule(organization = "com.typesafe")
  )

  lazy val loggingDeps = Seq(
    orgs.scalaLogging % "scala-logging_2.11" % versions.scalaLogging excludeAll exclusions.slf4j
  )

  lazy val unitTestDeps = Seq (
    "org.specs2" %% "specs2-core" % versions.specs2 % "test",
    "org.specs2" %% "specs2-mock" % versions.specs2 % "test",
    "org.scalatest" %% "scalatest" % versions.scalaTest % "test"
  )

  lazy val finatraDeps = Seq(
    orgs.twitter %% "finagle-http" % versions.finagle,
    (orgs.twitter + ".finatra") %% "finatra-http" % "2.1.5"
  )

  private lazy val exclusions = new {
    val slf4j = ExclusionRule(organization = "org.slf4j")
    val log4j = ExclusionRule(organization = "log4j")
    val slf4jLog4j = ExclusionRule(organization = "org.slf4j", name = "slf4j-log4j12")
    val mortbay = ExclusionRule(organization = "org.mortbay.jetty")
  }
}
