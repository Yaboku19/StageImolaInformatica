package it.unibo.tirocinio.martelli;

import it.unibo.tirocinio.martelli.controller.impl.ControllerImpl;

public class App {
    public static void main( String[] args ) throws Exception {
        new ControllerImpl().execute();
    }
}
