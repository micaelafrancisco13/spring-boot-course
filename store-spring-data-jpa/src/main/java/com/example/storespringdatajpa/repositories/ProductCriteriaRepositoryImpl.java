package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// @Repository marks this class as a Spring-managed component, allowing it to be
// detected during component scanning and injected where needed.
@AllArgsConstructor
@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    // Injects the standard JPA EntityManager, which is our gateway to interacting
    // with the persistence context and building queries.
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal min, BigDecimal max) {
        // The CriteriaBuilder is a factory used to construct query elements.
        var criteriaBuilder = entityManager.getCriteriaBuilder();

        // The CriteriaQuery defines the structure of our query (SELECT, FROM, WHERE, etc.).
        // We are building a query that will ultimately return a List of Products.
        var criteriaQuery = criteriaBuilder.createQuery(Product.class);

        // The Root object represents the 'FROM' clause of our query (e.g., "FROM products p")
        // and is our starting point for navigating the entity's attributes.
        var root = criteriaQuery.from(Product.class);

        // We'll build a list of conditions (Predicates) dynamically based on the
        // provided method parameters. A Predicate is the JPA Criteria term for a
        // single condition in a WHERE clause.
        var predicates = new ArrayList<Predicate>();

        if (name != null) {
            // This creates a 'LIKE' condition: WHERE p.name LIKE '%<name>%'
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if (min != null) {
            // This creates a '>=' condition: WHERE p.price >= <min>
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min));
        }
        if (max != null) {
            // This creates a '<=' condition: WHERE p.price <= <max>
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), max));
        }

        // Applies all the created predicates to the query's WHERE clause,
        // combining them with an 'AND'.
        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        // Finally, create and execute the query, returning the results.
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
