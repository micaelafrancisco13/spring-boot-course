package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.dtos.ProductSummary;
import com.example.storespringdatajpa.dtos.ProductSummaryDTO;
import com.example.storespringdatajpa.entities.Category;
import com.example.storespringdatajpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    /*
     * When you need to fetch only a subset of columns from an entity, you can use Projections.
     * This is highly efficient as it avoids loading the entire entity into memory.
     * Spring Data JPA supports two main types of projections: Interface-based and Class-based (DTOs).
     *
     * ## Interface vs. Class-Based Projections
     *
     * 1.  **Interface-Based Projection (`ProductSummary`)**:
     * - Spring Data automatically creates a proxy instance of this interface at runtime.
     * - The getter methods in the interface (e.g., `getId()`) directly map to the selected entity fields.
     * - This is a "closed" projection, meaning you can only access the properties defined by the getters.
     * - It is generally more lightweight and often the most performant option.
     *
     * 2.  **Class-Based Projection (`ProductSummaryDTO`)**:
     * - This uses a concrete class (a DTO) to hold the results.
     * - Spring Data instantiates this class for each result row, mapping the selected columns
     * to the constructor parameters.
     * - It's useful when you need to create more complex data objects or add custom logic to the DTO itself.
     *
     * The method below uses an interface, but it would work identically if you changed the
     * return type to `List<ProductSummaryDTO>`.
     */
    List<ProductSummary> findByCategory(Category category);
}