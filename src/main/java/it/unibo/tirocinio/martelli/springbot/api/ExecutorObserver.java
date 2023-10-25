package it.unibo.tirocinio.martelli.springbot.api;

public interface ExecutorObserver {
    String removeDatabaseElement();

    void addProblem(String url, String value, String type);
}
