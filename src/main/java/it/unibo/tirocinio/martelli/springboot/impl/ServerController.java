package it.unibo.tirocinio.martelli.springboot.impl;

import it.unibo.tirocinio.martelli.database.api.Database;
import it.unibo.tirocinio.martelli.database.impl.DatabaseSpark;
import it.unibo.tirocinio.martelli.search.api.Searcher;
import it.unibo.tirocinio.martelli.search.impl.SearcherImpl;
import it.unibo.tirocinio.martelli.read.impl.CrawlerFactory;
import it.unibo.tirocinio.martelli.springboot.api.SearcherObserver;
import it.unibo.tirocinio.martelli.springboot.api.ReaderObserver;
import it.unibo.tirocinio.martelli.typedata.ConfigData;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ServerController implements SearcherObserver, ReaderObserver {
    private final Database database;
    private final Searcher searcher;
    private final Thread searcherThread;
    private ConfigData configMap;
    private final CrawlerFactory crawlerFactory;
    private final List<String> dataBreachesList = new ArrayList<>();

    public ServerController() {
        database = new DatabaseSpark();
        crawlerFactory = new CrawlerFactory();
        searcher = new SearcherImpl(this);
        searcherThread = new Thread(searcher);
    }

    @Bean
    ApplicationRunner getConfigDataAndStartCrawlers(final ConfigData configMap) {
        return args -> {
            this.configMap = configMap;
            crawlerFactory.createCrawler(configMap.getCrawler(), this);
        };
    }

    @GetMapping("/show")
    public String show() {
        return configMap.getCrawler().toString();
    }

    @GetMapping("/info")
    public String getInfo() {
        return "for add any regex go on use the form when the application is running is impossible to add regex" +
                "\n for running the application go on http://localhost:8080/execute";
    }

    @GetMapping("/showDataBreaches")
    public String getDataBreaches() {
        return dataBreachesList
                .stream()
                .reduce((s, t) -> s + "\n" + t)
                .orElse("");
    }

    @GetMapping("/addRegex")
    public String addRegex(@RequestParam("regex")final String regex, @RequestParam("type")final String identifierType) {
        searcher.addRegex(regex, identifierType);
        searcherThread.start();
        return "added";
    }

    @GetMapping("/exit")
    public String exit() {
        database.closeDatabase();
        searcher.notify();
        System.exit(0);
        return "exit";
    }

    @Override
    public List<String> getAllDataBaseElements() {
        return database.getAllElements();
    }

    @Override
    public void addDataBreach(final String url, final String dataBreach, final String identifierType) {
        final String string = "url: " + url + " |||||| value: " + dataBreach + " |||||| type: " + identifierType;
        dataBreachesList.add(string);
    }

    @Override
    public void addDatabaseElement(final String url, final String data) {
        database.addElement(url, data);
        searcher.controlElement(url, data);
    }
}
