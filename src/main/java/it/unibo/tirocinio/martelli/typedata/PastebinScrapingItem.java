package it.unibo.tirocinio.martelli.typedata;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PastebinScrapingItem {

    private String scrapeUrl;
    private String fullUrl;
    private String date;
    private String key;
    private String size;
    private String expire;
    private String title;
    private String syntax;
    private String user;
    private String hits;

    public String getScrapeUrl() {
        return scrapeUrl;
    }

    @JsonProperty("scrape_url")
    public void setScrapeUrl(final String scrapeUrl) {
        this.scrapeUrl = scrapeUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    @JsonProperty("full_url")
    public void setFullUrl(final String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(final String key) {
        this.key = key;
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String size) {
        this.size = size;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(final String expire) {
        this.expire = expire;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(final String syntax) {
        this.syntax = syntax;
    }

    public String getUser() {
        return user;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(final String hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "PastebinScrapingItem [scrapeUrl=" + scrapeUrl + ", fullUrl=" + fullUrl + ", date=" + date + ", key="
                + key + ", size=" + size + ", expire=" + expire + ", title=" + title + ", syntax=" + syntax + ", user="
                + user + ", hits=" + hits + "]\n\n";
    }
}

