package tirocinio.curricolare.execute.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import tirocinio.curricolare.execute.api.Crawler;

public class Prova implements Crawler{
    private final static int CONNECTION_TIMEOUT = 1000;
    private final static int READ_TIMEOUT = 5000;

    private byte[] executeRequest(HttpRequestBase request) throws IOException {
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

private Object doGetRequest(String url) throws IOException {
    return new String(executeRequest(new HttpGet(url)));
}

@Override
public void execute(Map<String, Object> mapConfig) {
    for(String str : mapConfig.keySet()) {
        String url = "http://localhost:8080/" + str;
        try {
            Object obj = doGetRequest(url);
            System.out.println("ok");
        } catch (IOException e) {
            System.out.println("invalid url: " + url);
        }
    }
}
}
