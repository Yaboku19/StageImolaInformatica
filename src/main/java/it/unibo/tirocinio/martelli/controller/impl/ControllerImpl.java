package it.unibo.tirocinio.martelli.controller.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.unibo.tirocinio.martelli.controller.api.ExecutorObserver;
import it.unibo.tirocinio.martelli.controller.api.ReaderObserver;
import it.unibo.tirocinio.martelli.controller.api.Controller;
import it.unibo.tirocinio.martelli.database.api.Database;
import it.unibo.tirocinio.martelli.database.impl.DatabaseSpark;
import it.unibo.tirocinio.martelli.executor.api.Executor;
import it.unibo.tirocinio.martelli.executor.impl.ExecutorImpl;
import it.unibo.tirocinio.martelli.read.impl.CrawlerFactory;
import it.unibo.tirocinio.martelli.setup.api.Setup;
import it.unibo.tirocinio.martelli.setup.impl.SetupYml;

public class ControllerImpl implements Controller, ReaderObserver, ExecutorObserver {
     private final Database database;
     private final CrawlerFactory factory;
     private final Executor executor;

     private final Map<String, Object> config;
     private final Map<String, String> problems = new HashMap<>();

     @SuppressWarnings("unchecked")
     public ControllerImpl() throws Exception{
          database = new DatabaseSpark();
          factory = new CrawlerFactory();
          final Setup setup = new SetupYml();
          this.config = setup.readSetup(ClassLoader.getSystemResource("config/config.yml"));
          executor = new ExecutorImpl(this, (List<String>) config.get("regex"));
     }

     @Override
     @SuppressWarnings("unchecked")
     public void execute() throws Exception {
          factory.createCrawler((Map<String, Object>)config.get("crawler"), this);
          new Thread(executor).start();
     }

     @Override
     public void addDatabaseElement(final String element) {
          database.addElement(element);
     }

     @Override
     public String removeDatabaseElement() {
          if (database.isNotEmpty()) {
               return database.removeElement();
          } else {
               return "";
          }
     }

     @Override
     public void addProblem(final String url, final String value) {
          problems.put(url, value);
     }

     public String showProblems() {
          return problems.toString();
     }

     @Override
     public String showDatabse() {
          return database.getElements().toString();
     }
}
