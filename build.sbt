val rustVersion   = "1.49.0 (e1884a8e3 2020-12-29)"
val dottyVersion = "3.0.0-RC1"

val akkaVersion = "2.6.13"
val slf4jVersion        = "1.7.30"

lazy val nativeIODriver = (project in file("native-io-driver"))
  .settings(
    version := "0.1.0",
    Cargo.rustVersion := rustVersion,
    Compile / compile / compileInputs := (Compile / compile / compileInputs)
      .dependsOn(Cargo("build -p native-io-driver"))
      .value,
  )

lazy val root = project
  .in(file("."))
  .settings(
    name := "akka-event-io",
    version := "0.1.0",
    scalaVersion := dottyVersion,
    libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-slf4j"       % akkaVersion,
        "com.typesafe.akka" %% "akka-persistence" % akkaVersion
      ).map(_.withDottyCompat(scalaVersion.value)),
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api"    % slf4jVersion,
      "com.github.jnr" % "jnr-ffi" % "2.0.2",
      "com.novocode" % "junit-interface" % "0.11" % "test"),
    scalafmtOnCompile in Compile := true,
  )
