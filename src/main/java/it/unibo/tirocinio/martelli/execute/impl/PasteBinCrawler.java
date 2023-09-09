package it.unibo.tirocinio.martelli.execute.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.tirocinio.martelli.execute.api.Crawler;
import it.unibo.tirocinio.martelli.typedata.PastebinScrapingItem;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import static java.util.concurrent.TimeUnit.SECONDS;

@SuppressWarnings("unchecked")
public class PasteBinCrawler extends Crawler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    @Override
    public void execute(final Map<String, Object> config) throws IOException {
        setConnectionTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("connect"));
        setReadTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("read"));
        final List<PastebinScrapingItem> list = getScrapingItem(doGetRequest((String) config.get("url")));
        final List<String> insideList = new ArrayList<>();
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            scheduler.schedule(getReader(insideList, list.get(i)), i * getWaitingTime(config), SECONDS);
        }
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println(insideList);
            }
        }, list.size() * getWaitingTime(config), SECONDS);
        
    }

    private int getWaitingTime(final Map<String, Object> config) {
        return (Integer)config.get("waitingTime");
    }

    private List<PastebinScrapingItem> getScrapingItem(final String data) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(data);
        List<PastebinScrapingItem> list = new ArrayList<>();
        for (JsonNode element : jsonArray) {
            PastebinScrapingItem object = objectMapper.treeToValue(element, PastebinScrapingItem.class);
            list.add(object);
        }
        return list;
    }

    private Runnable getReader(final List<String> insideList, final PastebinScrapingItem item) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    insideList.add(doGetRequest(item.getScrapeUrl()));
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
