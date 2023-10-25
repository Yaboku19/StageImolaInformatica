package it.unibo.tirocinio.martelli;

import it.unibo.tirocinio.martelli.typedata.ConfigData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan("it.unibo.tirocinio.martelli.typedata")
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

}