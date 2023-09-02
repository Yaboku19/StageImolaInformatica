package tirocinio.curricolare.execute.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import tirocinio.curricolare.typedata.PastebinScrapingItem;

import tirocinio.curricolare.execute.api.Crawler;

public class Prova implements Crawler{
    private final static int CONNECTION_TIMEOUT = 1000;
    private final static int READ_TIMEOUT = 5000;

    private static byte[] executeRequest(HttpRequestBase request) throws IOException {
        RequestConfig.custom()
                .setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(READ_TIMEOUT)
                .setSocketTimeout(CONNECTION_TIMEOUT).build();
        
        try (CloseableHttpClient httpclient = HttpClientBuilder.create().build();
                CloseableHttpResponse response = httpclient.execute(request); 
                ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            
            response.getEntity().writeTo(bos);
            return bos.toByteArray();
        }
    }

    public static String doGetRequest(String url) throws IOException {
        return new String(executeRequest(new HttpGet(url)));
    }

    @Override
    public void execute(Map<String, Object> mapConfig) {
        for(String str : mapConfig.keySet()) {
            String url = "http://localhost:8080/" + str;
            try {
                JSONArray array = new JSONArray(doGetRequest(url));
                List<PastebinScrapingItem> listItem = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    listItem.add(new PastebinScrapingItem(array.getJSONObject(i)));
                }
                System.out.println(listItem);
            } catch (IOException e) {
                System.out.println("invalid url: " + url);
            }
        }
    }
}
