package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Category findByName(String name);
}