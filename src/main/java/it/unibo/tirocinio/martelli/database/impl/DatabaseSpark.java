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
          structType = structType.add("data", DataTypes.StringType, false);
          Dataset<Row> df = spark.createDataFrame(new ArrayList<Row>(), structType);
          this.structType = structType;
          this.database = df;
     }

     @Override
     public void addElement(final String element) {
          final long Oldversion = this.version;
          final Row newRow = RowFactory.create(element);
          Dataset<Row> databaseNew = this.database.union(spark.createDataFrame(Collections.singletonList(newRow), structType));
          if (!save(Oldversion, databaseNew)) {
               addElement(element);
          }
     }

     private synchronized boolean save(final long Oldversion, final Dataset<Row> databaseNew) {
          if (Oldversion == version) {
               this.database = databaseNew;
               version += 1;
               return true;
          }
          return false;
     }

     public static void main(String[] args) {
          DatabaseSpark database = new DatabaseSpark();
          database.show();
          database.addElement("ciao");
          database.addElement("casa");
          database.show();
          String value = database.removeElement();
          System.out.println(value);
          database.show();
          database.closeDatabase();
     }

     @Override
     public String removeElement() {
          final long oldversion = version;
          List<Row> list = new ArrayList<>(database.collectAsList());
          final String toRemove = list.get(0).toString();
          list.remove(0);
          Dataset<Row> databaseNew = spark.createDataFrame(list, structType);
          if (!save(oldversion, databaseNew)) {
               return removeElement();
          }
          return toRemove;
     }

     public void show() {
          database.show();
     }

     @Override
     public void closeDatabase() {
          spark.close();
     }
     
}
// [0-9a-z._-]+@(?:[^ .@]+\.)+[a-z]{2,4} per trovare l'e-mail