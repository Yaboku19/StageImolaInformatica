package it.unibo.tirocinio.martelli.execute.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import it.unibo.tirocinio.martelli.execute.api.Crawler;
import it.unibo.tirocinio.martelli.typedata.PastebinScrapingItem;

public class PasteBinCrawler extends Crawler{
    @Override
    public void execute(final Map<String, Object> config) throws IOException{
        System.out.println(config);
        final JSONArray ScrapingList = new JSONArray(doGetRequest("http://localhost:8080"
                                                + config.get("base_url")));
        final List<PastebinScrapingItem> scrapingItems = getScrapingItem(ScrapingList);
        System.out.println(scrapingItems);
        /*
            final List<String> insideList = new ArrayList<>();
            for(PastebinScrapingItem scrapeItem : listItem) {
                insideList.add(doGetRequest(scrapeItem.getScrapeUrl()));
            }
            System.out.println(insideList);
        } catch (IOException | SecurityException | IllegalArgumentException | JSONException e) {
            System.out.println("invalid url: " + url);
        }*/
    }

    private List<PastebinScrapingItem> getScrapingItem(final JSONArray scrapingList) {
        final List<PastebinScrapingItem> scrapingItems = new ArrayList<>();
        for (int i = 0; i < scrapingList.length(); i++) {
            JSONObject dataObject = (JSONObject) scrapingList.get(i);
            scrapingItems.add(new PastebinScrapingItem(
                getData("scrape_url", dataObject),
                getData("full_url", dataObject),
                getData("date", dataObject),
                getData("key", dataObject),
                getData("size", dataObject),
                getData("expire", dataObject),
                getData("title", dataObject),
                getData("syntax", dataObject),
                getData("user", dataObject),
                getData("hits", dataObject)
            ));
        }
        return scrapingItems;   
    }

    private String getData(final String field, final JSONObject dataObject) {
        return (String) dataObject.getString(field);
    }

    @Override
    public String getConfigPrefix() {
        return "pastebin";
    }
}
