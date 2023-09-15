package it.unibo.tirocinio.martelli.setup.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;
import it.unibo.tirocinio.martelli.setup.api.Setup;

public class SetupYml implements Setup{
     private URL setupPath = ClassLoader.getSystemResource("config/config.yml");

     public Map<String, Object> readSetup() throws FileNotFoundException, IOException, URISyntaxException {
          try (InputStream inputStream = new FileInputStream(new File(setupPath.toURI()))) {
               Yaml yaml = new Yaml();
               Map<String, Object> data = yaml.load(inputStream);
               return data;
          }
     }
}