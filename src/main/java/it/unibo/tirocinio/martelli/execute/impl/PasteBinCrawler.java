package it.unibo.tirocinio.martelli.execute.impl;

import java.util.Map;
import it.unibo.tirocinio.martelli.execute.api.Crawler;

public class PasteBinCrawler extends Crawler{
    @Override
    public void execute(final Map<String, Object> config) {
            System.out.println(config);
            /*final String url = "http://localhost:8080" + str;
            try {
                final JSONArray array = new JSONArray(doGetRequest(url));
                final List<PastebinScrapingItem> listItem = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    listItem.add(new PastebinScrapingItem((JSONObject)array.get(i)));
                }
                System.out.println(listItem);
                final List<String> insideList = new ArrayList<>();
                for(PastebinScrapingItem scrapeItem : listItem) {
                    insideList.add(doGetRequest(scrapeItem.getScrapeUrl()));
                }
                System.out.println(insideList);
            } catch (IOException | SecurityException | IllegalArgumentException | JSONException e) {
                System.out.println("invalid url: " + url);
            }*/
    }

    @Override
    public String getConfigPrefix() {
        return "pastebin";
    }
}
