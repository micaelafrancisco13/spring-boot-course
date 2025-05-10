package com.codewithmosh.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreApplication {

    /**
     * In every application, there are settings we'll need to configure, like which port the application runs on,
     * database credentials, or even custom settings for our application.
     */

    public static void main(String[] args) {
        SpringApplication.run(StoreApplication.class, args);
    }

}
