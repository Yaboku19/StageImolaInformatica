package it.unibo.tirocinio.martelli;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

import it.unibo.tirocinio.martelli.execute.impl.CrawlerFactory;

public class App {
    public static void main( String[] args ) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException, URISyntaxException {
        new CrawlerFactory().createCrawler();
    }
}
