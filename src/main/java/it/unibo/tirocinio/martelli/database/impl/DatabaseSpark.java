package it.unibo.tirocinio.martelli.database.impl;

import org.apache.spark.sql.SparkSession;
import it.unibo.tirocinio.martelli.database.api.Database;

public class DatabaseSpark implements Database{

     @Override
     public void addElement(String element) {
          
     }

     public static void main(String[] args) {
          final SparkSession spark = SparkSession.builder()
          .appName("Prova")
          .master("local[*]")
          .getOrCreate();
     }
     
}
