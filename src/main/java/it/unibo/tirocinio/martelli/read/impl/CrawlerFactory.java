package it.unibo.tirocinio.martelli.read.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import it.unibo.tirocinio.martelli.springbot.api.ReaderObserver;
import it.unibo.tirocinio.martelli.read.api.Crawler;

@SuppressWarnings("unchecked")
public class CrawlerFactory {
     private static int EXIT = -2;
     public void createCrawler(final Map<String,Object> config, final ReaderObserver model) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
                         InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
          final String prefix = config.get("prefix").toString();
          List<String> classNameList = new ArrayList<>();
          final Map<String, Object> prova = (Map<String, Object>)config.get("load_class");
          for (int i = 0; i >= 0; i++) {
               if (prova.get(Integer.toString(i)) == null) {
                    i = EXIT;
               } else {
                    classNameList.add((String) prova.get(Integer.toString(i)));
               }
          }
          for (final String name : classNameList) {
               Crawler crawler = (Crawler) Class.forName(prefix + name)
                              .getConstructor(new Class[]{}).newInstance();
               crawler.setVariable((Map<String, Object>) config.get(crawler.getConfigPrefix()), model);
               new Thread(crawler).start();
          }
     }
}
