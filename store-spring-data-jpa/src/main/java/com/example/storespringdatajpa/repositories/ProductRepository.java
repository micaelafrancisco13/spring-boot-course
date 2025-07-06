package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    /*
     * Method to find products whose price falls within a given range, sorted by name.
     * Below are three common ways to implement this with Spring Data JPA.
     */

    // =====================================================================================
    // Alternative 1: Derived Query Method
    // =====================================================================================
    /**
     * Finds products with a price between the specified minimum and maximum, ordered by name.
     * Spring Data JPA automatically "derives" the query from the method name.
     * This approach is concise and type-safe but less flexible for complex queries.
     */
    // List<Product> findByPriceBetweenOrderByName(BigDecimal min, BigDecimal max);


    // =====================================================================================
    // Alternative 2: Custom Query using @Query
    // =====================================================================================
    /*
     * Using the @Query annotation gives you full control over the query string.
     * This is useful for complex logic that cannot be expressed through a derived query name.
     * You can choose between native SQL or JPQL (Java Persistence Query Language).
     */

    // --- Using Native SQL ---
    /**
     * Finds products using a native SQL query.
     * The 'nativeQuery = true' flag indicates that this is a raw SQL statement specific
     * to the underlying database (e.g., PostgreSQL, MySQL).
     * Note: This approach can tie your repository to a specific database vendor.
     */
    // @Query(
    //         value = "SELECT * FROM products p WHERE p.price BETWEEN :min AND :max ORDER BY p.name",
    //         nativeQuery = true
    // )
    // List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);


    // --- Using JPQL (Recommended) ---

    /**
     * Finds products using a JPQL query.
     * JPQL is the standard, database-agnostic query language for JPA. It queries
     * against your entity model (e.g., "Product") rather than the database table (e.g., "products").
     * This is generally the preferred approach for custom queries due to their portability and readability.
     */
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max ORDER BY p.name")
    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);


    /**
     * Counts the number of products with a price between the specified minimum and maximum.
     * This JPQL query uses the count() aggregate function for efficiency, as it avoids
     * fetching the full entity objects.
     *
     * @param min The minimum price of the products to count.
     * @param max The maximum price of the products to count.
     * @return The total number of products within the specified price range.
     */
    @Query("SELECT COUNT(p) FROM Product p WHERE p.price BETWEEN :min AND :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);
}