organization  := "de.frosner"

version       := "0.1.0-SNAPSHOT"

name          := "jdbc-dump"

scalaVersion  := "2.10.5"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.2"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.5.2"

libraryDependencies += "com.databricks" %% "spark-avro" % "2.0.1"

libraryDependencies += "com.databricks" %% "spark-csv" % "1.3.0"

fork := true

javaOptions += "-Xmx2G"

javaOptions += "-XX:MaxPermSize=128m"

mainClass in assembly := Some("de.frosner.j2p.Launcher")

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    // TODO discard all that crap inside the final jar like html files
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("com", "google", "common", xs @ _*) => MergeStrategy.last
    //case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case "about.html" => MergeStrategy.rename
    case x => old(x)
  }
}
