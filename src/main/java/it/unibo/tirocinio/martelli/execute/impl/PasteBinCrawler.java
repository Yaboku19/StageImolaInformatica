package it.unibo.tirocinio.martelli.execute.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.unibo.tirocinio.martelli.execute.api.Crawler;
import it.unibo.tirocinio.martelli.typedata.PastebinScrapingItem;

@SuppressWarnings("unchecked")
public class PasteBinCrawler extends Crawler {
    @Override
    public void execute(final Map<String, Object> config) throws IOException {
        setConnectionTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("connect"));
        setReadTimeout((Integer)((Map<String, Object>)config.get("timeout"))
                            .get("read"));

        List<PastebinScrapingItem> list = getScrapingItem(doGetRequest((String) config.get("url")));
        final List<String> insideList = new ArrayList<>();
        for(PastebinScrapingItem item : list) {
            insideList.add(doGetRequest(item.getScrapeUrl()));
            /*
             * waiting time Executor
             */
        }
        System.out.println(insideList);
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

    @Override
    public String getConfigPrefix() {
        return "pastebin";
    }
}
