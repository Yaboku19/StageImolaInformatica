package tirocinio.curricolare;


import it.unibo.tirocinio.martelli.App;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ServerControllerTest {

    @BeforeEach
    public void settings() {
        String[] array = {};
        App.main(array);
    }
    @Test
    public void showDataBrechesTest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/addRegex?regex=%5Ba-z%5D&type=many"))
                .GET()
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        Awaitility.await().atMost(10, TimeUnit.SECONDS);
        request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/showDataBreaches"))
                .GET()
                .build();
        response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        assertFalse(response.body().isEmpty());
    }
}
