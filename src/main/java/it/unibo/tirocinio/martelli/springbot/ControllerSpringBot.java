package it.unibo.tirocinio.martelli.springbot;

import it.unibo.tirocinio.martelli.controller.impl.ControllerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import it.unibo.tirocinio.martelli.controller.api.Controller;

@RestController
public class ControllerSpringBot {
    private final Controller controller = new ControllerImpl();
    private final boolean isExecuted = false;

    public ControllerSpringBot() throws Exception {
    }

    @GetMapping("/overview")
    public String overview() {
        return "for add any regex go on use the form when the application is running is impossible to add regex" +
                "\n for running the application go on http://localhost:8080/execute";
    }

    @GetMapping("/execute")
    public String execute() throws Exception {
        controller.execute();
        return "running, for seeing any problems go to http://localhost:8080/showProblem";
    }

    @GetMapping("/showProblem")
    public String showProblems() {
        return controller.showProblems();
    }

    @GetMapping("/addRegex")
    public String addRegex(@RequestParam("regex")final String regex, @RequestParam("type")final String type) {
        if(isExecuted) {
            return "the application is runnning";
        }
        controller.addRegex(regex, type);
        return "added";
    }

    @GetMapping("/exit")
    public String exit() {
        controller.closeApplication();
        return "exit";
    }
}
