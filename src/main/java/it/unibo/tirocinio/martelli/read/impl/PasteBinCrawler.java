package it.unibo.tirocinio.martelli.read.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.tirocinio.martelli.controller.api.CrawlerObserver;
import it.unibo.tirocinio.martelli.read.api.Crawler;
import it.unibo.tirocinio.martelli.typedata.PastebinScrapingItem;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;

@SuppressWarnings("unchecked")
public class PasteBinCrawler extends Crawler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private CrawlerObserver controller;
    private Map<String, Object> config;

    @Override
    public void setVariable(final Map<String, Object> config, final CrawlerObserver controller) {
        this.controller = controller;
        this.config = config;
    }

    private void execute() {
        setConnectionTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("connect"));
        setReadTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("read"));
        List<PastebinScrapingItem> scrapingList = new ArrayList<>();
        try {
            scrapingList = getScrapingItem(doGetRequest((String) config.get("url")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < scrapingList.size(); i++) {
            scheduler.schedule(getReader(scrapingList.get(i)), 
                                i * getWaitingTime(config), SECONDS);
        }
        scheduler.shutdown();
    }

    private int getWaitingTime(final Map<String, Object> config) {
        return (Integer)config.get("waitingTime");
    }

    private List<PastebinScrapingItem> getScrapingItem(final String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(data);
        List<PastebinScrapingItem> scrapingList = new ArrayList<>();
        for (JsonNode node : jsonArray) {
            PastebinScrapingItem scraping = 
                    objectMapper.treeToValue(node, PastebinScrapingItem.class);
            scrapingList.add(scraping);
        }
        return scrapingList;
    }

    private Runnable getReader(final PastebinScrapingItem scraping) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    controller.addDatabaseElement(doGetRequest(scraping.getScrapeUrl()));
                    System.out.println("fatto");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public String getConfigPrefix() {
        return "pastebin";
    }

    @Override
    public void run(){  
        execute();
    }  
}
