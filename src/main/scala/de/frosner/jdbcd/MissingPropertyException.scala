package de.frosner.jdbcd

case class MissingPropertyException(propertyName: String) extends RuntimeException(s"Required property $propertyName is not specified")
