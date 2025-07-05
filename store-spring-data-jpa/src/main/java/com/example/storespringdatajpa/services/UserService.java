package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.Address;
import com.example.storespringdatajpa.entities.User;
import com.example.storespringdatajpa.repositories.AddressRepository;
import com.example.storespringdatajpa.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private final AddressRepository addressRepository;

    public void persistUserAddress() {
        var user = new User();
        user.setName("Kerwin");
        user.setEmail("kerwin31@gmail.com");
        user.setPassword("Password13!");

        var address = new Address();
        address.setStreet("123 Main St");
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("12345");

        user.addAddress(address);

        // By default, Hibernate does not propagate the persist operation.
        // Meaning, when it saves the user, it does not save its related entities like
        // the address.
        userRepository.save(user);

        // One way is to save the address separately:
        // addressRepository.save(address);

        // Another better way is by using the Cascade on the User entity Address field
    }
}
