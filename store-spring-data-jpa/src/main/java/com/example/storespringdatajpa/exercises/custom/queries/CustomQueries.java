package com.example.storespringdatajpa.exercises.custom.queries;

import com.example.storespringdatajpa.repositories.ProfileRepository;
import com.example.storespringdatajpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Service
public class CustomQueries {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public void setLoyaltyPoints() {
        int[] points = {5, 10, 20};

        var index = new AtomicInteger(0);
        userRepository.findAll()
                .stream()
                .limit(3)
                .forEach(user -> {
                    int i = index.getAndIncrement();
                    if (user.getProfile() != null) {
                        user.getProfile().setLoyaltyPoints(points[i]);
                    }
                });
    }

    @Transactional
    public void fetchProfileByLoyaltyPoints() {
        userRepository
                .findByLoyaltyPointsGreaterThan(3)
                .forEach(System.out::println);
    }
}
