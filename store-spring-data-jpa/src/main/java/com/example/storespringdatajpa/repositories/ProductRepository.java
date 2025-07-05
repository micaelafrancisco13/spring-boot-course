package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}