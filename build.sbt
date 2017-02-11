
name := "message-template"

organization := "org.neal.schneier"

conflictManager in Compile := ConflictManager.strict

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

initialize := {
  val _ = initialize.value
  if (sys.props("java.specification.version") != "1.8")
    sys.error("Java 8 is required for this project.")
}

parallelExecution in Test := false

scalaVersion := "2.11.8"

libraryDependencies ++= Settings.configDeps ++ Settings.finatraDeps ++ Settings.loggingDeps ++ Settings.unitTestDeps

mainClass in (Compile, run) := Some("org.neal.schneier.server.MessageTemplateServerMain")