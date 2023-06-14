package com.chandan.hibernateentitymappings.controller;

import com.chandan.hibernateentitymappings.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ConfigurationProperties(prefix = "app")
public class GreetingController {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private Environment environment;

    @Value("${app.app-name: default value}")
    private String greetingMessage;

    @Value("${other.value: DEFAULT}")
    private String defaultValue;

    @Value("${app.list: DEFAULT -LIST }")
    private List<String> list;

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        appConfig.iterateDbValues();
        return new ResponseEntity<>(greetingMessage + "\n" + defaultValue + "\n" + list + " "
                + appConfig.getDbValues() + " " + appConfig.getRabbitmq(), HttpStatus.OK);
    }

    @GetMapping("/environment")
    public ResponseEntity<String> envDetails() {
        return new ResponseEntity<>(environment.toString(), HttpStatus.OK);
    }

}
