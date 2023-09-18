package it.unibo.tirocinio.martelli.model.api;

public interface CrawlerObserver {
     void addDatabaseElement(String element);

     String removeDatabaseElement();
}
