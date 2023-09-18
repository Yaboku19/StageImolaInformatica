package it.unibo.tirocinio.martelli.read.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unibo.tirocinio.martelli.model.api.CrawlerObserver;
import it.unibo.tirocinio.martelli.read.api.Crawler;
import it.unibo.tirocinio.martelli.typedata.PastebinScrapingItem;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;

@SuppressWarnings("unchecked")
public class PasteBinCrawler extends Crawler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private CrawlerObserver model;
    @Override
    public void execute(final Map<String, Object> config, final CrawlerObserver model) throws IOException {
        this.model = model;
        setConnectionTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("connect"));
        setReadTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("read"));
        final List<PastebinScrapingItem> scrapingList = 
                getScrapingItem(doGetRequest((String) config.get("url")));
        final List<String> scrapeList = new ArrayList<>();
        for (int i = 0; i < scrapingList.size(); i++) {
            scheduler.schedule(getReader(scrapeList, scrapingList.get(i)), 
                                i * getWaitingTime(config), SECONDS);
        }
        scheduler.schedule(new Runnable() {         // da cancellare in futuro
            @Override
            public void run() {
                System.out.println(scrapeList);
            }
        }, scrapingList.size() * getWaitingTime(config), SECONDS);
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

    private Runnable getReader(final List<String> scrapeList, final PastebinScrapingItem scraping) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    scrapeList.add(doGetRequest(scraping.getScrapeUrl()));
                    // addElement to database
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
}
