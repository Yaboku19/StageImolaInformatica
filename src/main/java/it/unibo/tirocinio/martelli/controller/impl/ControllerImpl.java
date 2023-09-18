package it.unibo.tirocinio.martelli.controller.impl;

import it.unibo.tirocinio.martelli.controller.api.Controller;
import it.unibo.tirocinio.martelli.model.api.Model;
import it.unibo.tirocinio.martelli.model.impl.ModelImpl;

public class ControllerImpl implements Controller {
     private final Model model;

     public ControllerImpl() {
          model = new ModelImpl();
     }

     @Override
     public void execute() throws Exception {
          model.startReading();
     }
     
}
