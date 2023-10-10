package it.unibo.tirocinio.martelli.controller.api;

import java.util.List;

public interface Controller {
     void execute() throws Exception;

     String showProblems();

     String showDatabse();
}
