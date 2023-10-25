package it.unibo.tirocinio.martelli.executor.impl;

import it.unibo.tirocinio.martelli.springbot.api.ExecutorObserver;
import it.unibo.tirocinio.martelli.executor.api.Executor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutorImpl extends Executor {
    private final ExecutorObserver controller;
    private final Map<String,String> regexes;
    public ExecutorImpl(final ExecutorObserver controller) {
        this.regexes = new HashMap<>();
        this.controller = controller;
    }
    @Override
    protected void startReading() {
        while (!Thread.interrupted()) {
            final String value = controller.removeDatabaseElement();
            if (!"".equals(value)) {
                final String[] values = value
                        .replace("[", "").replace("]", "").split("\\,");
                for (final String regex : regexes.keySet()) {
                    final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                    final Matcher matcher = pattern.matcher(values[1]);
                    if (matcher.find()) {
                        controller.addProblem(values[0], values[1], regexes.get(regex));
                    }
                }
            }
        }
    }

    @Override
    public void addRegex(final String regex, final String type) {
        regexes.put(regex, type);
    }
}
/*
  [0-9a-z._-]+@(?:[^ .@]+\.)+[a-z]{2,4}
 */
