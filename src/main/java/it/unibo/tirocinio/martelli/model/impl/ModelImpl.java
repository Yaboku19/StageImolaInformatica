package it.unibo.tirocinio.martelli.model.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;

import it.unibo.tirocinio.martelli.database.api.Database;
import it.unibo.tirocinio.martelli.database.impl.DatabaseSpark;
import it.unibo.tirocinio.martelli.model.api.CrawlerObserver;
import it.unibo.tirocinio.martelli.model.api.Model;
import it.unibo.tirocinio.martelli.read.impl.CrawlerFactory;

public class ModelImpl implements Model, CrawlerObserver{
     private final Database database;
     private final CrawlerFactory factory;
     private final URL setupPath = ClassLoader.getSystemResource("config/config.yml");

     public ModelImpl() {
          database = new DatabaseSpark();
          factory = new CrawlerFactory();
     }

     @Override
     public void startReading() throws InstantiationException, IllegalAccessException, 
                    IllegalArgumentException, InvocationTargetException, NoSuchMethodException, 
                    SecurityException, ClassNotFoundException, IOException, URISyntaxException {
          factory.createCrawler(setupPath, this);
     }

     @Override
     public void addDatabaseElement(final String element) {
          database.addElement(element);
     }

     @Override
     public String removeDatabaseElement() {
          return database.removeElement();
     }
}
