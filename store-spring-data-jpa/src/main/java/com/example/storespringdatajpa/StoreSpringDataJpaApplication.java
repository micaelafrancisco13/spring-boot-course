package com.example.storespringdatajpa;

import com.example.storespringdatajpa.entities.Address;
import com.example.storespringdatajpa.entities.Profile;
import com.example.storespringdatajpa.entities.Tag;
import com.example.storespringdatajpa.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StoreSpringDataJpaApplication.class, args);
        demonstrate();
    }

    private static void demonstrate() {
        var user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("Password13!");

        // address
        var address = Address.builder()
                .street("123 Main St")
                .city("Springfield")
                .state("MA")
                .zipCode("02115")
                .build();

        user.addAddress(address);

        // tags
        user.addTag("Tag 1");

        // profile
        var profile = Profile.builder().bio("Bio").build();
        profile.setUser(user);
        user.setProfile(profile);

        System.out.println("User: " + user);
    }
}
