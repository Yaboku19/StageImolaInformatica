package it.unibo.tirocinio.martelli.read.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.unibo.tirocinio.martelli.springboot.api.ReaderObserver;
import it.unibo.tirocinio.martelli.read.api.Crawler;

@SuppressWarnings("unchecked")
public class CrawlerFactory {

     public void createCrawler(final Map<String,Object> configMap, final ReaderObserver controller) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
                         InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
          final String prefix = configMap.get("prefix").toString();
          final List<String> classNameList = new ArrayList<>();
          ((Map<String, Object>)configMap.get("load_class"))
                  .forEach((x,y) -> classNameList.add(y.toString()));
          for (final String name : classNameList) {
               Crawler crawler = (Crawler) Class.forName(prefix + name)
                              .getConstructor(new Class[]{}).newInstance();
               crawler.setConfig((Map<String, Object>) configMap.get(crawler.getConfigPrefix()), controller);
               new Thread(crawler).start();
          }
     }
}
