package it.unibo.tirocinio.martelli.read.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.tirocinio.martelli.read.api.Crawler;
import it.unibo.tirocinio.martelli.typedata.PastebinScrapingItem;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;

public class PasteBinCrawler extends Crawler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    protected void execute() {
        List<PastebinScrapingItem> scrapingList = new ArrayList<>();
        try {
            scrapingList = getScrapingItem(doGetRequest((String) getConfig().get("url")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < scrapingList.size(); i++) {
            scheduler.schedule(getReader(scrapingList.get(i)), 
                                i * getWaitingTime(), SECONDS);
        }
        scheduler.shutdown();
    }

    private int getWaitingTime() {
        return (Integer)getConfig().get("waitingTime");
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
                    getController().addDatabaseElement(doGetRequest(scraping.getScrapeUrl()));
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
