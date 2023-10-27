package it.unibo.tirocinio.martelli.search.api;

public abstract class Searcher implements Runnable {
    protected abstract void search();

    public abstract void addRegex(String regex, String type);

    public abstract void controlElement(String url, String element);
    @Override
    public void run() {
        search();
    }
}
