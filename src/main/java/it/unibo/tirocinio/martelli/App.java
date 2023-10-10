package it.unibo.tirocinio.martelli;

import it.unibo.tirocinio.martelli.controller.impl.ControllerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main( String[] args ) throws Exception {
        SpringApplication.run(App.class, args);
    }
}