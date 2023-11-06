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

    protected void read() {
        List<PastebinScrapingItem> scrapingList = new ArrayList<>();
        try {
            scrapingList = getScrapingItem(doGetRequest((String) getConfigMap().get("url")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < scrapingList.size(); i++) {
            scheduler.schedule(getAdder(scrapingList.get(i)),
                    (long) i * getWaitingTime(), SECONDS);
        }
        scheduler.shutdown();
    }

    private int getWaitingTime() {
        return (Integer)getConfigMap().get("waitingTime");
    }

    private List<PastebinScrapingItem> getScrapingItem(final String data) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonArray = objectMapper.readTree(data);
        final List<PastebinScrapingItem> scrapingList = new ArrayList<>();
        for (final JsonNode node : jsonArray) {
            PastebinScrapingItem scraping = 
                    objectMapper.treeToValue(node, PastebinScrapingItem.class);
            scrapingList.add(scraping);
        }
        return scrapingList;
    }

    private Runnable getAdder(final PastebinScrapingItem scraping) {
        return () -> {
                try {
                    getController().addDatabaseElement(scraping.getScrapeUrl(),doGetRequest(scraping.getScrapeUrl()));
                } catch (IOException e) {

                }
        };
    }

    @Override
    public String getConfigPrefix() {
        return "pastebin";
    } 
}
