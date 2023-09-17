package it.unibo.tirocinio.martelli.controller.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import it.unibo.tirocinio.martelli.controller.api.Controller;
import it.unibo.tirocinio.martelli.read.impl.CrawlerFactory;

public class ControllerImpl implements Controller{

     @Override
     public void execute() throws InstantiationException, IllegalAccessException, IllegalArgumentException, 
                    InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, 
                    IOException, URISyntaxException {
          new CrawlerFactory().createCrawler();
     }
     
}
