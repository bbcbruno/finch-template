import com.gilt.sbt.newrelic.NewRelic.autoImport._
import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
//import JavaAppPackaging.autoImport._
import sbt.Keys._
//import scoverage._

lazy val buildSettings = Seq(
  organization := "com.redbubble",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.2"
)

lazy val circeVersion = "0.8.0"
lazy val catsVersion = "0.9.0"
lazy val mouseVersion = "0.9"
// The version numbers for Finagle, Twitter, Finch & Catbird *must* work together. See the Finch build.sbt for known good versions.
lazy val finagleVersion = "6.45.0"
lazy val twitterServerVersion = "1.30.0"
lazy val finchVersion = "0.15.1"
lazy val catBirdVersion = "0.15.0"
lazy val featherbedVersion = "0.3.1"
lazy val logbackVersion = "1.2.3"
lazy val specsVersion = "3.9.4"
lazy val jodaTimeVersion = "2.9.9"
lazy val jodaConvertVersion = "1.8.2"
lazy val scalaCacheVersion = "0.9.4"
lazy val scalaUriVersion = "0.4.16"
lazy val fetchVersion = "0.6.2"
lazy val slf4jVersion = "1.7.25"
lazy val rollbarVersion = "0.5.4"
lazy val scalaJava8CompatVersion = "0.8.0"
lazy val gattlingVersion = "2.2.5"
lazy val nrVersion = "3.41.0"
lazy val metricsVersion = "3.2.3"
lazy val metricsNewRelicVersion = "1.1.1"

lazy val cats = "org.typelevel" %% "cats-core" % catsVersion
lazy val scalaJava8Compat = "org.scala-lang.modules" %% "scala-java8-compat" % scalaJava8CompatVersion
lazy val circeCore = "io.circe" %% "circe-core" % circeVersion
lazy val circeGeneric = "io.circe" %% "circe-generic" % circeVersion
lazy val circeParser = "io.circe" %% "circe-parser" % circeVersion
lazy val mouse = "com.github.benhutchison" %% "mouse" % mouseVersion
lazy val finagleHttp = "com.twitter" %% "finagle-http" % finagleVersion
lazy val finagleStats = "com.twitter" %% "finagle-stats" % finagleVersion
lazy val finchCore = "com.github.finagle" %% "finch-core" % finchVersion
lazy val finchCirce = "com.github.finagle" %% "finch-circe" % finchVersion
lazy val catBird = "io.catbird" %% "catbird-finagle" % catBirdVersion
lazy val scalaUri = "com.netaporter" %% "scala-uri" % scalaUriVersion
lazy val fetch = "com.47deg" %% "fetch" % fetchVersion
lazy val featherbedCore = "io.github.finagle" %% "featherbed-core" % featherbedVersion
lazy val featherbedCirce = "io.github.finagle" %% "featherbed-circe" % featherbedVersion
lazy val metricsCore = "io.dropwizard.metrics" % "metrics-core" % metricsVersion
lazy val metricsNewRelic = "com.palominolabs.metrics" % "metrics-new-relic" % metricsNewRelicVersion
lazy val scalacacheCaffeine = "com.github.cb372" %% "scalacache-caffeine" % scalaCacheVersion
lazy val logbackCore = "ch.qos.logback" % "logback-core" % logbackVersion
lazy val logbackClassic = "ch.qos.logback" % "logback-classic" % logbackVersion
lazy val slf4jApi = "org.slf4j" % "slf4j-api" % slf4jVersion
lazy val slf4jJul = "org.slf4j" % "jul-to-slf4j" % slf4jVersion
lazy val rollbar = "com.rollbar" % "rollbar" % rollbarVersion
lazy val jodaTime = "joda-time" % "joda-time" % jodaTimeVersion
lazy val jodaConvert = "org.joda" % "joda-convert" % jodaConvertVersion
lazy val twitterServer = "com.twitter" %% "twitter-server" % twitterServerVersion
lazy val newRelic = "com.newrelic.agent.java" % "newrelic-api" % nrVersion
lazy val gattlingHighCharts = ("io.gatling.highcharts" % "gatling-charts-highcharts" % gattlingVersion % "it,test")
    .excludeAll(ExclusionRule("org.scala-lang.modules", "scala-xml_2.11"))
    .excludeAll(ExclusionRule("org.scala-lang.modules", "scala-java8-compat_2.11"))
    .excludeAll(ExclusionRule("org.scala-lang.modules", "scala-parser-combinators_2.11"))
lazy val gattlingTest = ("io.gatling" % "gatling-test-framework" % gattlingVersion % "it,test")
    .excludeAll(ExclusionRule("org.scala-lang.modules", "scala-xml_2.11"))
    .excludeAll(ExclusionRule("org.scala-lang.modules", "scala-java8-compat_2.11"))
    .excludeAll(ExclusionRule("org.scala-lang.modules", "scala-parser-combinators_2.11"))
lazy val specsCore = "org.specs2" %% "specs2-core" % specsVersion
lazy val specsScalacheck = "org.specs2" %% "specs2-scalacheck" % specsVersion

// https://tpolecat.github.io/2014/04/11/scalac-flags.html
lazy val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:reflectiveCalls",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture",
  "-Xlint",
  //"-Yno-predef",
  //"-Ywarn-unused-import", // gives false positives
  "-Xfatal-warnings",
  "-Ywarn-value-discard",
  "-Ypartial-unification"
)

lazy val commonDependencies = Seq(
  scalaJava8Compat,
  cats,
  circeCore,
  circeGeneric,
  circeParser,
  jodaTime,
  jodaConvert,
  mouse,
  finagleHttp,
  finagleStats,
  finchCore,
  finchCirce,
  catBird,
  scalaUri,
  fetch,
  //featherbedCore,
  //featherbedCirce,
  metricsCore,
  metricsNewRelic,
  scalacacheCaffeine,
  logbackCore,
  logbackClassic,
  slf4jApi,
  slf4jJul,
  rollbar
)

lazy val coreDependencies = Seq(
  twitterServer,
  finagleHttp,
  finagleStats,
  finchCore,
  finchCirce,
  catBird,
  scalaUri,
  fetch,
  //featherbedCore,
  //featherbedCirce,
  newRelic
)

lazy val perfDependencies = Seq(
  gattlingHighCharts,
  gattlingTest
)

lazy val testDependencies = Seq(
  specsCore,
  specsScalacheck
)

lazy val baseSettings = Seq(
  libraryDependencies ++= testDependencies.map(_ % "test"),
  resolvers ++= Seq(
    Resolver.jcenterRepo,
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots"),
    "Twitter" at "http://maven.twttr.com"
  ),
  scalacOptions ++= compilerOptions,
  scalacOptions in Test ++= Seq("-Yrangepos"),
  scalacOptions in(Compile, console) += "-Yrepl-class-based",
  testOptions in Test += Tests.Setup(() => System.setProperty("ENV", "test"))
)

lazy val commonSettings = buildSettings ++ baseSettings ++ Seq(
  libraryDependencies ++= (commonDependencies ++ testDependencies)
)
lazy val coreSettings = buildSettings ++ baseSettings ++ Seq(
  libraryDependencies ++= coreDependencies
)
lazy val perfSettings = buildSettings ++ baseSettings ++ Defaults.itSettings ++ Seq(
  libraryDependencies ++= perfDependencies
)
lazy val finchTemplateSettings = buildSettings ++ baseSettings ++ Seq(
  libraryDependencies ++= coreDependencies,
  mainClass in Compile := Some("com.redbubble.finchtemplate.App"),
  aggregate in run := false,
  aggregate in reStart := false,
  coverageMinimum := 25.0,
  coverageFailOnMinimum := true,
  coverageExcludedPackages := "com\\.redbubble\\.util\\.spec\\.*",
  newrelicAppName := "Finch Template",
  newrelicVersion := nrVersion,
  newrelicCustomTracing := true,
  newrelicIncludeApi := true
)

lazy val common = (project in file("common"))
    .settings(commonSettings)
lazy val core = (project in file("core"))
    .settings(coreSettings)
    .dependsOn(common % "test->test;compile->compile")
lazy val perf = (project in file("perf"))
    .settings(perfSettings)
    .configs(IntegrationTest)
    .dependsOn(core % "test->test;compile->compile")
    .enablePlugins(GatlingPlugin)
lazy val api = project.in(file("."))
    .settings(name := "finch-template", moduleName := "finch-template")
    .settings(finchTemplateSettings)
    .aggregate(common, core, perf)
    .dependsOn(common, core, perf)
    .enablePlugins(JavaAppPackaging)
    .enablePlugins(NewRelic)

shellPrompt in ThisBuild := { state =>
  s"${scala.Console.MAGENTA}${Project.extract(state).currentRef.project}> ${scala.Console.RESET}"
}

// Note. We aggregate coverage as a separate command in CI, see https://github.com/scoverage/sbt-scoverage#multi-project-reports
val ciCommands = List(
  "clean",
  // TODO Re-enable this.
  //  "scalastyle",
  //  "test:scalastyle",
  "compile",
  "test:compile",
  "coverage",
  "test",
  "coverageReport",
  "stage"
)
addCommandAlias("ci", ciCommands.mkString(";", ";", ""))
