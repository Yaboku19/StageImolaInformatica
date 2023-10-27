package it.unibo.tirocinio.martelli.database.impl;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import it.unibo.tirocinio.martelli.database.api.Database;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseSpark implements Database {
     private final SparkConf conf = new SparkConf()
               .setAppName("Database")
               .setMaster("local[*]");
     private final SparkSession spark =  SparkSession.builder()
               .config(conf)
               .getOrCreate();
     private Dataset<Row> database;
     private final StructType structType;
     private long version = 1;

     public DatabaseSpark() {
          StructType structType = new StructType();
          structType = structType.add("url", DataTypes.StringType, false);
          structType = structType.add("data", DataTypes.StringType, false);
          Dataset<Row> df = spark.createDataFrame(new ArrayList<>(), structType);
          this.structType = structType;
          this.database = df;
     }

     @Override
     public void addElement(final String url, final String data) {
          final long oldVersion = this.version;
          final Row newRow = RowFactory.create(url, data);
          final Dataset<Row> databaseNew = this.database.union(spark.createDataFrame(Collections.singletonList(newRow), structType));
          if (save(oldVersion, databaseNew)) {
               addElement(url, data);
          }
     }

     private synchronized boolean save(final long oldVersion, final Dataset<Row> databaseNew) {
          if (oldVersion == version) {
               this.database = databaseNew;
               version += 1;
               return false;
          }
          return true;
     }

     @Override
     public List<String> getAllElements() {
          return new ArrayList<>(database.collectAsList()).stream().map(Row::toString).collect(Collectors.toList());
     }

     @Override
     public void closeDatabase() {
          spark.close();
     }

     public static void main(final String[] str) {
          DatabaseSpark database = new DatabaseSpark();
          database.addElement("io", "schifo");
          database.addElement("uaa", "bleah");
          System.out.println(database.getAllElements().get(1));
     }
}
// [0-9a-z._-]+@(?:[^ .@]+\.)+[a-z]{2,4} per trovare l'e-mail