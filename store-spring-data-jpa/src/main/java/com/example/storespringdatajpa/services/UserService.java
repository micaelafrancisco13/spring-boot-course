package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.Address;
import com.example.storespringdatajpa.entities.Profile;
import com.example.storespringdatajpa.entities.User;
import com.example.storespringdatajpa.repositories.AddressRepository;
import com.example.storespringdatajpa.repositories.ProductRepository;
import com.example.storespringdatajpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final AddressRepository addressRepository;
    private final ProductRepository productRepository;
    private UserRepository userRepository;

    /*
     * Here's what’s going on when you don’t annotate your method with @Transactional:
     *
     * 1. userRepository.findByEmail(...) executes the query.
     * 2. Spring Data JPA opens a temporary transaction just for that method call
     *    and closes it immediately after the result is returned.
     * 3. You now hold a detached User entity — it is no longer managed by the persistence context.
     * 4. When System.out.println(userFound) (or any code) tries to access user.getTags(),
     *    Hibernate attempts to lazily initialize the tags collection.
     * 5. But since the Hibernate Session is already closed at that point,
     *    it throws a LazyInitializationException.
     *
     * Solution: Annotate the method with @Transactional to keep the session open
     * during the entire method execution so lazy-loaded fields can be accessed safely.
     */
    @Transactional
    public void fetchUserWithTags() {
        var userFound = userRepository
                .findByEmail("jane@email")
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // You might notice Hibernate also fetches the wishlists and tags, even though we only
        // specified "addresses" in the @EntityGraph.
        // This happens because the @ToString annotation on the User entity accesses those fields,
        // triggering their lazy loading.
        System.out.println("User found: " + userFound);
    }

    @Transactional
    public void addProductToWishlist() {
        var userFound = userRepository
                .findById(UUID.fromString("6e9b6388-d3db-43cc-a1e8-73c03f2d4ebb"))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userFound.setWishlists(new LinkedHashSet<>(productRepository.findAll()));
    }

    @Transactional
    public void deleteUserAddress() {
        var userFound = userRepository
                .findById(UUID.fromString("6e9b6388-d3db-43cc-a1e8-73c03f2d4ebb"))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userFound.getAddresses()
                .stream()
                .findFirst()
                .ifPresent(userFound::removeAddress);

        userRepository.save(userFound);
    }

    @Transactional
    public void addSampleUsers() {
        var users = new ArrayList<User>();

        users.add(createUser(
                "John", "john@email", "<PASSWORD>",
                createAddress("123 Main St", "Springfield", "MA", "02114"),
                createProfile("I am a software developer", "123-456-7890", null, 1000)
        ));

        users.add(createUser(
                "Jane", "jane@email", "<PASSWORD>",
                createAddress("456 Main St", "Springfield", "MA", "02114"),
                createProfile("I am a software engineer", "123-456-7890", null, 0)
        ));

        users.add(createUser(
                "Jill", "jill@email", "<PASSWORD>",
                createAddress("789 Main St", "Springfield", "MA", "02114"),
                createProfile("I am a software architect", "123-456-7890", null, 0)
        ));

        users.add(createUser(
                "Jessica", "jessica@email", "<PASSWORD>",
                null,
                createProfile("I am a software tester", "123-456-7890", null, 0)
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

    private Profile createProfile(String bio, String phoneNumber, LocalDate dob, int loyaltyPoints) {
        Profile profile = new Profile();
        profile.setBio(bio);
        profile.setPhoneNumber(phoneNumber);
        profile.setDateOfBirth(dob);
        if (loyaltyPoints != 0) {
            profile.setLoyaltyPoints(loyaltyPoints);
        }
        return profile;
    }

}
