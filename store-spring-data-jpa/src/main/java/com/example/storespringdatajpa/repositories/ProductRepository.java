package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

/**
 * Extends JpaRepository for standard CRUD and JpaSpecificationExecutor to enable
 * dynamic, criteria-based queries. The Specification API is used as a more
 * reusable and less verbose alternative to the raw Criteria API for building
 * dynamic queries.
 */
public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
}
