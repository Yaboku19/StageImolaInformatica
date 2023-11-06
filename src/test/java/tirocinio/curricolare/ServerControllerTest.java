package tirocinio.curricolare;


import it.unibo.tirocinio.martelli.springboot.impl.ServerController;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerControllerTest {
    @Test
    public void showDataBrechesTest() {
        ServerController controller = new ServerController();
        controller.addRegex("[a-z]", "mail");
        System.out.println("waiting...");
        Awaitility.await()
                .atMost(5, TimeUnit.SECONDS);
        System.out.println(controller.getDataBreaches() + " [0-9a-z._-]+@(?:[^ .@]+\\.)+[a-z]{2,4}");
    }
}
