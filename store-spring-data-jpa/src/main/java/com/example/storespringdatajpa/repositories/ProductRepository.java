package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    // update the price of all products in a given category

    // Informs Spring Data JPA that this query is an UPDATE or DELETE operation.
    // This annotation is required for any query that modifies the database state.
    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategoryId(@Param("categoryId") UUID categoryId, @Param("newPrice") BigDecimal newPrice);
}