package tirocinio.curricolare.typedata;

import org.json.JSONObject;

public class PastebinScrapingItem {

    @Override
    public String toString() {
        return "PastebinScrapingItem [scrapeUrl=" + scrapeUrl + ", fullUrl=" + fullUrl + ", date=" + date + ", key="
                + key + ", size=" + size + ", expire=" + expire + ", title=" + title + ", syntax=" + syntax + ", user="
                + user + ", hits=" + hits + "]\n\n";
    }

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


    public PastebinScrapingItem(JSONObject object) {
        this.scrapeUrl = object.getString("scrape_url");
        this.fullUrl = object.getString("full_url");
        this.date = object.getString("date");
        this.key = object.getString("key");
        this.size = object.getString("size");
        this.expire = object.getString("expire");
        this.title = object.getString("title");
        this.syntax = object.getString("syntax");
        this.user = object.getString("user");
        this.hits = object.getString("hits");
    }

    public String getScrapeUrl() {
        return scrapeUrl;
    }

    public void setScrapeUrl(String scrapeUrl) {
        this.scrapeUrl = scrapeUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }   
}

