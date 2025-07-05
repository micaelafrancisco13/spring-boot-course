package com.example.storespringdatajpa;

import com.example.storespringdatajpa.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreSpringDataJpaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(StoreSpringDataJpaApplication.class, args);
        var userService = context.getBean(UserService.class);
        userService.showEntityStates();
    }
}

/// / address
//var address = Address.builder()
//        .street("123 Main St")
//        .city("Springfield")
//        .state("MA")
//        .zipCode("02115")
//        .build();
//
//        user.addAddress(address);
//
/// / tags
//        user.addTag("Tag 1");
//
/// / profile
//var profile = Profile.builder().bio("Bio").build();
//        profile.setUser(user);
//        user.setProfile(profile);
