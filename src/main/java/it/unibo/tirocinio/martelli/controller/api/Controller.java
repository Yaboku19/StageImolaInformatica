package it.unibo.tirocinio.martelli.controller.api;

public interface Controller {
     void execute() throws Exception;

     String showProblems();

     String showDatabse();

     void addRegex(String regex, String type);

     void closeApplication();
}
