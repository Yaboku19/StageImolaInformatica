package it.unibo.tirocinio.martelli.springbot.impl;

import it.unibo.tirocinio.martelli.database.api.Database;
import it.unibo.tirocinio.martelli.database.impl.DatabaseSpark;
import it.unibo.tirocinio.martelli.executor.api.Executor;
import it.unibo.tirocinio.martelli.executor.impl.ExecutorImpl;
import it.unibo.tirocinio.martelli.read.impl.CrawlerFactory;
import it.unibo.tirocinio.martelli.springbot.api.ExecutorObserver;
import it.unibo.tirocinio.martelli.springbot.api.ReaderObserver;
import it.unibo.tirocinio.martelli.typedata.ConfigData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ServerController implements ExecutorObserver, ReaderObserver {
    private boolean isExecuted = false;
    private final Database database;
    private final CrawlerFactory factory;
    private final Executor executor;
    private final Thread executorThread;
    private ConfigData data = new ConfigData();
    private final List<String> problems = new ArrayList<>();

    public ServerController() throws Exception {
        database = new DatabaseSpark();
        factory = new CrawlerFactory();
        executor = new ExecutorImpl(this);
        executorThread = new Thread(executor);
    }

    @Bean
    ApplicationRunner applicationRunner( ConfigData data) {
        return args -> this.data = data ;
    }
    @GetMapping("/show")
    public String show() {
        return data.getCrawler().toString();
    }
    @GetMapping("/overview")
    public String overview() {
        return "for add any regex go on use the form when the application is running is impossible to add regex" +
                "\n for running the application go on http://localhost:8080/execute";
    }

    @GetMapping("/execute")
    public String execute() throws Exception {
        if (isExecuted) {
            return "application is running yet";
        }
        factory.createCrawler(data.getCrawler(), this);
        executorThread.start();
        isExecuted = true;
        return "running, for seeing any problems go to http://localhost:8080/showProblem";
    }

    @GetMapping("/showProblem")
    public String showProblems() {
        return problems.toString();
    }

    @GetMapping("/addRegex")
    public String addRegex(@RequestParam("regex")final String regex, @RequestParam("type")final String type) {
        if(isExecuted) {
            return "the application is runnning";
        }
        executor.addRegex(regex, type);
        return "added";
    }

    @GetMapping("/exit")
    public String exit() {
        database.closeDatabase();
        executor.notify();
        System.exit(0);
        return "exit";
    }

    @Override
    public String removeDatabaseElement() {
        if (database.isNotEmpty()) {
            return database.removeElement();
        } else {
            return "";
        }
    }

    @Override
    public void addProblem(String url, String value, String type) {
        final String string = "url: " + url + "\nvalue: " + value + "\ntype: " + type + "\n";
        problems.add(string);
    }

    @Override
    public void addDatabaseElement(String url, String data) {
        database.addElement(url, data);
    }
}
