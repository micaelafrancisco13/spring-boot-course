package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    /*
     * Before we jump into writing code, let's take a few minutes and talk about what the Spring Framework is,
     * what it offers, and how Spring Boot builds on it to make our lives easier.
     *
     * Let's start with the Spring Framework.
     * It's a popular framework for building Java applications.
     * Think of it as a toolbox for building applications.
     * It has a lot of modules, each designed to handle a specific task.
     * These modules are broadly categorized into a few different layers.
     *
     * SPRING FRAMEWORK LAYERS
     *   WEB | DATA
     *      AOP
     *      CORE
     *      TEST
     *
     * At the core, we have modules for handling dependency injection and managing objects.
     * We'll talk about that in detail in the next section.
     *
     * In the web layer, we have modules for building web applications.
     * With these modules, we can handle web requests,
     * process data, and return responses, whether it's HTML for a web page or JSON for an API.
     *
     * In the data layer, we have modules for working with databases,
     * whether you're using SQL, NoSQL, or even in-memory databases.
     * We also have a module
     * for adding cross-cutting features like logging or security without cluttering the main code.
     * This is called AOP or aspect-oriented programming.
     *
     * We also have a module for testing Spring components.
     *
     * Now the beauty of Spring is that it's modular,
     * so you can pick and choose the modules you need for your project.
     * It's powerful and flexible, which is why so many developers love it.
     *
     * Now, while the Spring Framework is powerful, using it often involves a lot of configuration.
     * For example, if you want to build a web application,
     * you might need to set up a web server, configure routing,
     * and manage dependencies manually.
     * This can make development slower and more complex.
     *
     * That's where Spring Boot comes in.
     * Think of Spring Boot as a layer on top of the Spring Framework
     * that takes care of all the tedious setups for you.
     * It simplifies Spring development by providing sensible defaults and ready-to-use features.
     *
     * So why does this matter?
     * With Spring Boot, you can go from idea to working application in minutes instead of hours.
     * It saves you time, reduces boilerplate code,
     * and lets you focus on what really matters: building great features for your users.
     *
     * By the way, the Spring Framework is just one part of a larger family of projects in the Spring ecosystem.
     * These projects extend the capabilities of the framework to address specific needs.
     *
     * For example, we have Spring Data for simplifying database access.
     * We have Spring Security for adding authentication and authorization to our applications.
     * We have Spring Batch for batch processing,
     * Spring Cloud for building microservices and distributed systems.
     * We have Spring Integration for simplifying messaging and integration between systems and more.
     *
     * Now that you understand what the Spring Framework and Spring Boot are
     * and how they fit in the larger Spring ecosystem,
     * let's start building.
     */

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
