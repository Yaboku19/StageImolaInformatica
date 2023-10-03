package it.unibo.tirocinio.martelli.database.api;

public interface Database {
     void addElement(String element);

     String removeElement();

     void closeDatabase();

     void show();

     boolean isNotEmpty();
}
