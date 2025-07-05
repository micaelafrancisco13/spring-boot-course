package com.example.storespringdatajpa;

import com.example.storespringdatajpa.entities.User;
import com.example.storespringdatajpa.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;

@SpringBootApplication
public class StoreSpringDataJpaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(StoreSpringDataJpaApplication.class, args);
//        demonstrate(context);
    }

    private static void demonstrate(ConfigurableApplicationContext context) {
        var user = new User();
        user.setName("Ela");
        user.setEmail("ela13@gmail.com");
        user.setPassword("Password13!");

        var userRepository = context.getBean(UserRepository.class);
        // Hibernate will generate the appropriate SQL statements at run-time
         userRepository.save(user);

        var userFound = userRepository
                .findById(UUID.fromString("dbcfd4ac-0db6-4530-a942-4fe70f0d8792"))
                .orElseThrow(() -> new IllegalArgumentException("User does not exist"));
        System.out.println("User found: " + userFound.getEmail());

        userRepository.findAll().forEach(u -> System.out.println(u.getName()));
        userRepository.deleteById(UUID.fromString("dbcfd4ac-0db6-4530-a942-4fe70f0d8792"));
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
//// tags
//        user.addTag("Tag 1");
//
//// profile
//var profile = Profile.builder().bio("Bio").build();
//        profile.setUser(user);
//        user.setProfile(profile);
