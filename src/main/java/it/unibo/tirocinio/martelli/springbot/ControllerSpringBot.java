package it.unibo.tirocinio.martelli.springbot;
import it.unibo.tirocinio.martelli.controller.impl.ControllerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import it.unibo.tirocinio.martelli.controller.api.Controller;

import java.util.List;

@RestController
public class ControllerSpringBot {
    private final Controller controller = new ControllerImpl();
    @GetMapping("/hello")
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("/execute")
    public String execute() throws Exception {
        controller.execute();
        return "ok";
    }

    @GetMapping("/show")
    public List<String> show() {
        return controller.showDatabase();
    }
}
