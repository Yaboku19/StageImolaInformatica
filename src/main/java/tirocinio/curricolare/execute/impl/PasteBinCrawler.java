package tirocinio.curricolare.execute.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tirocinio.curricolare.execute.api.Crawler;
import tirocinio.curricolare.typedata.PastebinScrapingItem;

public class PasteBinCrawler extends Crawler{
    @Override
    public void execute(String str) {
            String url = "http://localhost:8080/" + str;
            try {
                JSONArray array = new JSONArray(doGetRequest(url));
                List<PastebinScrapingItem> listItem = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    listItem.add(new PastebinScrapingItem((JSONObject)array.get(i)));
                }
                System.out.println(listItem);
                List<String> insideList = new ArrayList<>();
                for(PastebinScrapingItem scrapeItem : listItem) {
                    insideList.add(getInformation(scrapeItem.getScrapeUrl()));
                }
                System.out.println(insideList);
            } catch (IOException | SecurityException | IllegalArgumentException | JSONException e) {
                System.out.println("invalid url: " + url);
            }
    }

    private String getInformation(String url) throws IOException {
        return doGetRequest(url);
    }
}
