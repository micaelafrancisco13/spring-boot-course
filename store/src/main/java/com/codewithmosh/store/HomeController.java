package com.codewithmosh.store;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    // Spring Boot will inject the value of this property from the application.properties
    // into the appName variable.
    @Value( "${spring.application.name}")
    private String appName;

    @GetMapping()
    public String index() {
        System.out.println("Home page for " + appName);
        return "index.html";
    }
}
