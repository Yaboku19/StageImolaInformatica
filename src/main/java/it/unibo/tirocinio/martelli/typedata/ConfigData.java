package it.unibo.tirocinio.martelli.typedata;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.Map;

@ConfigurationProperties(prefix = "settings")
@ConfigurationPropertiesScan
public class ConfigData {

    private Map<String, Object> crawler;

    public Map<String, Object> getCrawler() {
        return crawler;
    }

    public void setCrawler(Map<String, Object> crawler) {
        this.crawler = crawler;
    }
}
