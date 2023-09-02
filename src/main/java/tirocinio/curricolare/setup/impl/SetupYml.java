package tirocinio.curricolare.setup.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import tirocinio.curricolare.setup.api.Setup;

public class SetupYml implements Setup{
     private URL setupPath = ClassLoader.getSystemResource("config/config.yml");

     public Map<String, Object> readSetup() {
          InputStream inputStream;
          try {
               inputStream = new FileInputStream(new File(setupPath.toURI()));
               Yaml yaml = new Yaml();
               Map<String, Object> data = yaml.load(inputStream);
               return data;
          } catch (FileNotFoundException | URISyntaxException e) {
               return new HashMap<>();
          }
     }
}