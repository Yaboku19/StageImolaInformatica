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
import it.unibo.tirocinio.martelli.springboot.api.ReaderObserver;

@SuppressWarnings("unchecked")
public abstract class Crawler implements Runnable{
     private int connectionTimeout = 0;
     private int readTimeout = 0;
     private ReaderObserver controller;
     private Map<String, Object> configMap;

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
         read();
     }

     public void setConfig(final Map<String, Object> configMap, final ReaderObserver controller) {
          this.controller = controller;
          this.configMap = configMap;
          this.connectionTimeout = (Integer)((Map<String, Object>)configMap.get("timeout"))
               .get("connect");
          this.readTimeout = (Integer)((Map<String, Object>)configMap.get("timeout"))
               .get("read");
     }

     protected Map<String, Object> getConfigMap() {
          return configMap;
     }

     protected ReaderObserver getController() {
          return controller;
     }

     protected abstract void read();

     public abstract String getConfigPrefix();
}
