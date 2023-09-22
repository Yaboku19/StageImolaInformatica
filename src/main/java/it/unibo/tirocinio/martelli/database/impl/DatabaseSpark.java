package it.unibo.tirocinio.martelli.database.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import it.unibo.tirocinio.martelli.database.api.Database;

public class DatabaseSpark implements Database {

     @Override
     public synchronized void addElement(String element) {
          System.out.println("added");
     }

     public static void main(String[] args) {
          try(final var spark = SparkSession.builder()
                              .appName("Prova")
                              .master("local[*]")
                              .getOrCreate();
                    final var  sc = new JavaSparkContext(spark.sparkContext())) {
               final var data = Stream.iterate(1, x -> x + 1)
                    .limit(5)
                    .collect(Collectors.toList());
               System.out.println(data);
               final var myrdd = sc.parallelize(data);
               System.out.println(myrdd.count());
          }
          
     }

     @Override
     public String removeElement() {
          return "";
     }
     
}
