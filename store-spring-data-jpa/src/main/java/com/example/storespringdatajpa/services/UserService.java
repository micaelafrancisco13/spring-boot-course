package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.Address;
import com.example.storespringdatajpa.entities.Profile;
import com.example.storespringdatajpa.entities.User;
import com.example.storespringdatajpa.repositories.AddressRepository;
import com.example.storespringdatajpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public void deleteUser() {
        System.out.println("DELETING USER");
        // This is going to throw an error because on the Address db,
        // we forgot to set the On Delete to Cascade.
//        userRepository.deleteById(
//                UUID.fromString("e37c5b84-0dea-48a0-b642-267f6eb3e28d")
//        );

        userRepository.deleteAll();

        // What if we can't directly alter the database?
        // This is where we can enable cascading on the Model level
        // Go to the User class
    }

    @Transactional
    public void addSampleUsers() {
        var users = new ArrayList<User>();

        users.add(createUser(
                "John", "john@email", "<PASSWORD>",
                createAddress("123 Main St", "Springfield", "MA", "02114"),
                createProfile("I am a software developer", "123-456-7890", null, "1000")
        ));

        users.add(createUser(
                "Jane", "jane@email", "<PASSWORD>",
                createAddress("456 Main St", "Springfield", "MA", "02114"),
                createProfile("I am a software engineer", "123-456-7890", null, null)
        ));

        users.add(createUser(
                "Jill", "jill@email", "<PASSWORD>",
                createAddress("789 Main St", "Springfield", "MA", "02114"),
                createProfile("I am a software architect", "123-456-7890", null, null)
        ));

        users.add(createUser(
                "Jessica", "jessica@email", "<PASSWORD>",
                null,
                createProfile("I am a software tester", "123-456-7890", null, null)
        ));

        users.add(createUser(
                "Jones", "jones@email", "<PASSWORD>",
                createAddress("123 Main St", "Springfield", "MA", "02114"),
                null
        ));

        userRepository.saveAll(users);
    }

    private User createUser(String name, String email, String password, Address address, Profile profile) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        if (address != null) {
            user.addAddress(address);
        }

        if (profile != null) {
            user.setProfile(profile);
        }

        return user;
    }

    private Address createAddress(String street, String city, String state, String zipCode) {
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setZipCode(zipCode);
        return address;
    }

    private Profile createProfile(String bio, String phoneNumber, LocalDate dob, String loyaltyPoints) {
        Profile profile = new Profile();
        profile.setBio(bio);
        profile.setPhoneNumber(phoneNumber);
        profile.setDateOfBirth(dob);
        if (loyaltyPoints != null) {
            profile.setLoyaltyPoints(loyaltyPoints);
        }
        return profile;
    }

}
