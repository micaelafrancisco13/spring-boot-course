package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.dtos.ProductSummaryDTO;
import com.example.storespringdatajpa.entities.Category;
import com.example.storespringdatajpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    // You can use projections in a @Query to return only specific fields.
    // In the commented-out example below, even though we might want a projection like ProductSummary,
    // it still fetches full Product entities because of "select p".
    // To optimize this, you should explicitly select only the fields you need.

    // @Query("select p from Product p where p.category = :category")

    // If you want to use a DTO like ProductSummaryDTO for projection,
    // you must instantiate it directly in the JPQL query using a constructor expression.
    // This ensures only the selected fields are fetched from the database.
    @Query("select new com.example.storespringdatajpa.dtos.ProductSummaryDTO(p.id, p.name) " +
            "from Product p where p.category = :category")
    List<ProductSummaryDTO> findByCategory(@Param("category") Category category);
}
