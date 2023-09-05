package it.unibo.tirocinio.martelli;

import java.lang.reflect.InvocationTargetException;

import it.unibo.tirocinio.martelli.execute.impl.CrawlerFactory;

public class App {
    public static void main( String[] args ) {
        try {
            new CrawlerFactory().createCrawler();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException 
                | InvocationTargetException| NoSuchMethodException | SecurityException
                | ClassNotFoundException e) {
            e.printStackTrace();
        };
    }
}
