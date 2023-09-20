package it.unibo.tirocinio.martelli.controller.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import it.unibo.tirocinio.martelli.controller.api.CrawlerObserver;
import it.unibo.tirocinio.martelli.controller.api.Controller;
import it.unibo.tirocinio.martelli.database.api.Database;
import it.unibo.tirocinio.martelli.database.impl.DatabaseSpark;
import it.unibo.tirocinio.martelli.read.impl.CrawlerFactory;

public class ControllerImpl implements Controller, CrawlerObserver{
     private final Database database;
     private final CrawlerFactory factory;
     private final URL setupPath = ClassLoader.getSystemResource("config/config.yml");

     public ControllerImpl() {
          database = new DatabaseSpark();
          factory = new CrawlerFactory();
     }

     @Override
     public void execute() throws InstantiationException, IllegalAccessException, 
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
