package it.unibo.tirocinio.martelli.executor.api;

public abstract class Executor implements Runnable {
    protected abstract void startReading();

    @Override
    public void run() {
        startReading();
    }
}
