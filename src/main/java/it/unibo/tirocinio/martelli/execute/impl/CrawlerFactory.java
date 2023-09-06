package it.unibo.tirocinio.martelli.execute.impl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import it.unibo.tirocinio.martelli.execute.api.Crawler;
import it.unibo.tirocinio.martelli.setup.impl.SetupYml;

@SuppressWarnings("unchecked")
public class CrawlerFactory {
     public void createCrawler() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException {
          final Map<String, Object> config = 
               (Map<String, Object>)new SetupYml().readSetup().get("crawler");
          final String prefix = config.get("prefix").toString();
          final List<String> classNameList = (List<String>) config.get("load_class");
          for (final String name : classNameList) {
               Crawler crawler = (Crawler) Class.forName(prefix + name)
                              .getConstructor(new Class[]{}).newInstance();
               crawler.execute((Map<String, Object>)config.get(crawler.getConfigPrefix()));
          }
     }
}