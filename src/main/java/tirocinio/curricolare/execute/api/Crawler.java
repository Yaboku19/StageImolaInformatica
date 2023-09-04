package tirocinio.curricolare.execute.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public abstract class Crawler {
     private final int CONNECTION_TIMEOUT = 1000;
     private final int READ_TIMEOUT = 5000;

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

     public String doGetRequest(String url) throws IOException {
          return new String(executeRequest(new HttpGet(url)));
     }
     public abstract void execute(String str);

}
