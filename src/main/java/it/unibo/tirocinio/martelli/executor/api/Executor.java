package it.unibo.tirocinio.martelli.executor.api;

public abstract class Executor implements Runnable {
    protected abstract void startReading();

    public abstract void addRegex(String regex, String type);
    @Override
    public void run() {
        startReading();
    }
}
