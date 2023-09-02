package tirocinio.curricolare.run;

import java.util.Map;

import tirocinio.curricolare.setup.impl.SetupYml;

public class RunApp {
     public RunApp() {
          Map<String, Object> map = new SetupYml().readSetup();
          System.out.println(map);
     }
}
