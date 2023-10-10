package it.unibo.tirocinio.martelli.read.impl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import it.unibo.tirocinio.martelli.controller.api.ReaderObserver;
import it.unibo.tirocinio.martelli.read.api.Crawler;
import it.unibo.tirocinio.martelli.setup.impl.SetupYml;

@SuppressWarnings("unchecked")
public class CrawlerFactory {
     public void createCrawler(final Map<String,Object> config, final ReaderObserver model) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
                         InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, 
                         IOException, URISyntaxException {
          final String prefix = config.get("prefix").toString();
          final List<String> classNameList = (List<String>) config.get("load_class");
          for (final String name : classNameList) {
               Crawler crawler = (Crawler) Class.forName(prefix + name)
                              .getConstructor(new Class[]{}).newInstance();
               crawler.setVariable((Map<String, Object>) config.get(crawler.getConfigPrefix()), model);
               new Thread(crawler).start();
          }
     }
}
