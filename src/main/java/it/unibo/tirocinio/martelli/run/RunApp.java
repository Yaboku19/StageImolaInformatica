package it.unibo.tirocinio.martelli.run;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import it.unibo.tirocinio.martelli.execute.api.Crawler;
import it.unibo.tirocinio.martelli.setup.impl.SetupYml;

@SuppressWarnings("unchecked")
public class RunApp {
     public RunApp() {
          Map<String, Object> mapData = new SetupYml().readSetup();
          System.out.println(mapData);
          for (String key : mapData.keySet()) {
               try {
                    Class<? extends Crawler> typeClass = 
                         (Class<? extends Crawler>) Class.forName(mapData.get(key).toString());
                    Crawler crawler = 
                         typeClass.getConstructor(new Class[]{})
                         .newInstance();
                    crawler.execute(key);
               } catch (ClassNotFoundException |SecurityException | NoSuchMethodException |
                         InstantiationException | IllegalAccessException |
                         IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
               }
          }
          /*
           * for(var key : map.keySet()) {
           *   Class typeClass = Class.forName(mapConfig.get(str).toString());
           *   -
           *   -+
           *   -
           * }
           */
     }
}
