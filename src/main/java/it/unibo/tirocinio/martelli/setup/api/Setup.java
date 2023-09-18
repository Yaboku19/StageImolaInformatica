package it.unibo.tirocinio.martelli.setup.api;

import java.util.Map;
import java.net.URL;

public interface Setup {
     public Map<String, Object> readSetup(URL setupPath) throws Exception;
}
