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

import it.unibo.tirocinio.martelli.model.api.CrawlerObserver;

public abstract class Crawler extends Thread{
     private int connectionTimeout = 0;
     private int readTimeout = 0;

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

     protected void setConnectionTimeout(final int data) {
          this.connectionTimeout = data;
     }
     
     protected void setReadTimeout(final int data) {
          this.readTimeout = data;
     }

     public abstract void setVariable(Map<String, Object> config, CrawlerObserver model) throws IOException;

     public abstract String getConfigPrefix();
}
