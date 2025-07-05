package com.example.storespringdatajpa;

import com.example.storespringdatajpa.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreSpringDataJpaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(StoreSpringDataJpaApplication.class, args);
        var userService = context.getBean(UserService.class);
        userService.deleteUserAddress();
    }
}