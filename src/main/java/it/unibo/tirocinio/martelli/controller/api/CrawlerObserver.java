package it.unibo.tirocinio.martelli.controller.api;

public interface CrawlerObserver {
     void addDatabaseElement(String element);

     String removeDatabaseElement();
}
