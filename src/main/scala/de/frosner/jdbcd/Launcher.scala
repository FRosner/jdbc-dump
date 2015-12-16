package de.frosner.jdbcd

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}

object Launcher extends App {

  def getProperty(name: String, default: String = null) =
    Option(System.getProperty(name)).getOrElse(if (default == null) throw MissingPropertyException(name) else default)

  val url = getProperty("jdbcd.url")
  val table = getProperty("jdbcd.table")
  val outputFormat = getProperty("jdbcd.outputFormat", "parquet")
  val outputPath = getProperty("jdbcd.outputPath", s"output.$outputFormat")

  val sparkConf = new SparkConf().setAppName("jdbcd").setMaster("local[*]")
  val sc = new SparkContext(sparkConf)
  val sql = new SQLContext(sc)

  val df = sql.read.format("jdbc").options(Map(
    "url" -> url,
    "dbtable" -> table)
  ).load()

  outputFormat match {
    case "parquet" => df.write.parquet(outputPath)
    case "json" => df.write.json(outputPath)
    case "csv" => df.write.format("com.databricks.spark.csv").option("header", "true").save(outputPath)
    case "avro" => df.write.format("com.databricks.spark.avro").save(outputPath)
    case unknown => throw new IllegalArgumentException(s"Unknown output format $unknown")
  }

}
