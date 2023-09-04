package tirocinio.curricolare.run;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import tirocinio.curricolare.execute.api.Crawler;
import tirocinio.curricolare.setup.impl.SetupYml;

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
                         .newInstance(new Class[]{});
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
