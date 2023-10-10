package it.unibo.tirocinio.martelli.executor.impl;

import it.unibo.tirocinio.martelli.controller.api.ExecutorObserver;
import it.unibo.tirocinio.martelli.executor.api.Executor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutorImpl extends Executor {
    private final ExecutorObserver controller;
    private final List<String> regexes;
    public ExecutorImpl(final ExecutorObserver controller, final List<String> regexes) {
        this.regexes = regexes;
        this.controller = controller;
    }
    @Override
    protected void startReading() {
        int i = 0;
        while (true) {
            final String value = controller.removeDatabaseElement();
            if (!"".equals(value)) {
                final String[] values = value
                        .replace("[", "").replace("]", "").split("\\,");
                i++;
                for (final String regex : regexes) {
                    final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                    final Matcher matcher = pattern.matcher(values[1]);
                    if (matcher.find()) {
                        controller.addProblem(values[0], values[1]);
                    }
                }
            }
        }
    }
}
