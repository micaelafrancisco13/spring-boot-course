package com.example.storespringdatajpa.repositories.specifications;

import com.example.storespringdatajpa.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/**
 * A factory class for creating reusable Specification instances.
 * This class encapsulates the logic for building individual query fragments,
 * which can then be combined to form complex, dynamic database queries.
 */
public class ProductSpecification {

    /**
     * Creates a Specification to find products whose names contain the given string, case-insensitively.
     *
     * @param name The substring to search for within the product name.
     * @return A Specification for a case-insensitive "LIKE" query.
     */
    public static Specification<Product> hasName(String name) {
        // A Specification is a functional interface. The lambda expression implements its `toPredicate` method.
        // This method defines how to convert the specification into a JPA Predicate (a query condition).
        return (root, query, criteriaBuilder) -> {
            // To make the search case-insensitive, we convert both the database column
            // and the input parameter to the lowercase before comparison.
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    /**
     * Creates a Specification to find products with a price greater than or equal to the specified value.
     *
     * @param price The minimum price to compare against.
     * @return A Specification for a "greater than or equal to" query.
     */
    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }

    /**
     * Creates a Specification to find products with a price less than or equal to the specified value.
     *
     * @param price The maximum price to compare against.
     * @return A Specification for a "less than or equal to" query.
     */
    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }
}