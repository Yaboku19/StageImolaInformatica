package it.unibo.tirocinio.martelli.database.api;

import java.util.List;

public interface Database {
     void addElement(String url, String data);

     void closeDatabase();

     List<String> getAllElements();
}
