package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    /**
     * If you're not using IntelliJ, you can run this application using Maven.
     * First, we have to open a terminal window and go to our project folder.
     * If you don't have Maven globally installed on your machine, you can use the Maven wrapper.
     * With this Maven wrapper, we can run our Spring Boot application using `./mvnw spring-boot:run`.
     */

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
