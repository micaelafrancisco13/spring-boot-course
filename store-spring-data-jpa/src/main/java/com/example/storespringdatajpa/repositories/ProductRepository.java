package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Calls a PostgreSQL stored function named `find_products_by_price_range`.
     * <p>
     * A stored function (or stored procedure) is a reusable SQL logic block that resides in the database.
     * It can accept input parameters and return results, such as a single value or a set of rows.
     * <p>
     * In this case, the function returns all products whose price falls within the specified range.
     * <p>
     * Why use a stored function:
     * <br/>
     * --------------------------
     * <br/>
     * - Centralizes complex SQL logic within the database.
     * <br/>
     * - Improves performance by reducing the need to transfer raw query logic over the network.
     * <br/>
     * - Allows for reuse and easier maintenance of logic.
     * <br/>
     * <p>
     * Note:
     * - The `@Query` annotation uses `nativeQuery = true` to indicate that this is a native SQL query.
     * - The placeholders `?1` and `?2` map to the method parameters `min` and `max`, respectively.
     *
     * @param min The minimum price (inclusive).
     * @param max The maximum price (inclusive).
     * @return A list of products within the given price range, ordered by name (as defined in the function).
     */
    @Query(value = "SELECT * FROM find_products_by_price_range(?1, ?2)", nativeQuery = true)
    List<Product> findProducts(BigDecimal min, BigDecimal max);
}
