package it.unibo.tirocinio.martelli.springboot.api;

import java.util.List;

public interface SearcherObserver {
    List<String> getAllDataBaseElements();

    void addDataBreach(String url, String value, String type);
}
