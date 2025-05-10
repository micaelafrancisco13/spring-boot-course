package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    /*
     * In this lesson, we'll create our first controller to handle requests to the home page
     * and serve a simple HTML file.
     * But before we start coding, let's take a moment to understand
     * the basics of Spring MVC, which is the foundation of how we handle web requests in Spring Boot.
     *
     * Spring MVC stands for Model-View-Controller.
     * It's part of the Spring Framework that helps us build web applications.
     * It provides a clean way to separate different parts of our application,
     * making it easier to manage and scale.
     *
     * The Model is where our application's data lives.
     * It represents the business logic and is usually connected to a database
     * or other data sources.
     * In Spring Boot, the model can be a simple Java class.
     *
     * The View is what the user sees.
     * It's the HTML, CSS, or JavaScript rendered in the browser.
     * In Spring MVC, views can be static files or dynamically generated using tools like Thymeleaf.
     *
     * The Controller is like a traffic controller.
     * It handles incoming requests from the user, interacts with the model to get data,
     * and then tells the view what to display.
     */

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
