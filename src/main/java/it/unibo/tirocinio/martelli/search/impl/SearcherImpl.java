package it.unibo.tirocinio.martelli.search.impl;

import it.unibo.tirocinio.martelli.springboot.api.SearcherObserver;
import it.unibo.tirocinio.martelli.search.api.Searcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearcherImpl extends Searcher {
    private final SearcherObserver controller;
    private final Map<String,String> regexMap;

    public SearcherImpl(final SearcherObserver controller) {
        this.regexMap = new HashMap<>();
        this.controller = controller;
    }
    @Override
    protected void search() {
        final List<String> elementsList = controller.getAllDataBaseElements();
        for (final String element : elementsList) {
            final String[] values = element
                    .replace("[", "").replace("]", "").split("\\,");
            controlElement(values[0], values[1]);
        }
    }

    @Override
    public void addRegex(final String regex, final String type) {
        regexMap.put(regex, type);
    }

    @Override
    public void controlElement(final String url, final String element) {
        for (final String regex : regexMap.keySet()) {
            final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            final Matcher matcher = pattern.matcher(element);
            if (matcher.find()) {
                controller.addDataBreach(url, element, regexMap.get(regex));
            }
        }
    }
}
/*
  [0-9a-z._-]+@(?:[^ .@]+\.)+[a-z]{2,4}
 */
