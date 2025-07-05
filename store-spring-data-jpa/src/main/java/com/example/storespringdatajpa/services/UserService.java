package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.repositories.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final AddressRepository addressRepository;

    @Transactional
    public void showRelatedEntities() {
        var address = addressRepository
                .findById(UUID.fromString("afb3e880-62d4-41c6-a968-913fe1b7fd85"))
                .orElseThrow(() -> new IllegalStateException("Address not found"));

        System.out.println("Address: " + address);
        System.out.println("User email: " + address.getUser().getEmail());
        System.out.println("User bio: " + address.getUser().getProfile().getBio());
    }
}
