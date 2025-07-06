package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.exercises.custom.queries.CustomQueries;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class Demo {
    private final CustomQueries customQueries;

    public void show() {
//        customQueries.setLoyaltyPoints();
        customQueries.fetchProfileByLoyaltyPoints();
    }
}
