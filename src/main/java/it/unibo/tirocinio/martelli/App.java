package it.unibo.tirocinio.martelli;

import it.unibo.tirocinio.martelli.execute.impl.CrawlerFactory;

public class App {
    public static void main( String[] args ) {
        new CrawlerFactory().createCrawler();
    }
}
