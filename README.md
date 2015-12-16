## JDBC Dump

Application for dumping tables in several storage formats through a JDBC Spark connection.

## Build

`sbt assembly`

## Use

```sh
java -Djdbcd.url="jdbc:sqlite:test.sqlite" \
-Djdbcd.table="test" \
-Djdbcd.outputFormat="json" \
-cp "jdbc-dump-assembly-x.y.z.jar:sqlite-jdbc-3.8.11.2.jar" \
de.frosner.jdbcd.Launcher
```
