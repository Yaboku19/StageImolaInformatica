package it.unibo.tirocinio.martelli.read.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import it.unibo.tirocinio.martelli.controller.api.CrawlerObserver;

@SuppressWarnings("unchecked")
public abstract class Crawler implements Runnable{
     private int connectionTimeout = 0;
     private int readTimeout = 0;
     private CrawlerObserver controller;
     private Map<String, Object> config;

     private byte[] executeRequest(final HttpRequestBase request) throws IOException {
          RequestConfig.custom()
                    .setConnectTimeout(connectionTimeout)
                    .setConnectionRequestTimeout(readTimeout
          )
                    .setSocketTimeout(connectionTimeout).build();
          
          try (CloseableHttpClient httpclient = HttpClientBuilder.create().build();
                    CloseableHttpResponse response = httpclient.execute(request); 
                    ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
               
               response.getEntity().writeTo(bos);
               return bos.toByteArray();
          }
     }

     protected String doGetRequest(final String url) throws IOException {
          return new String(executeRequest(new HttpGet(url)));
     }

     @Override
     public void run(){  
         execute();
     }

     public void setVariable(final Map<String, Object> config, final CrawlerObserver controller) {
          this.controller = controller;
          this.config = config;
          this.connectionTimeout = (Integer)((Map<String, Object>)getConfig().get("timeout"))
               .get("connect");
          this.readTimeout = (Integer)((Map<String, Object>)getConfig().get("timeout"))
               .get("read");
     }

     protected Map<String, Object> getConfig() {
          return config;
     }

     protected CrawlerObserver getController() {
          return controller;
     }

     protected abstract void execute();

     public abstract String getConfigPrefix();
}
