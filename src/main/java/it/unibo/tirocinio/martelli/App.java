package it.unibo.tirocinio.martelli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan("it.unibo.tirocinio.martelli.typedata")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

}