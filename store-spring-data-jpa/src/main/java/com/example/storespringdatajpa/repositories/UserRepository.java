package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.dtos.UserSummary;
import com.example.storespringdatajpa.entities.Profile;
import com.example.storespringdatajpa.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * ### Why is `@Query` needed here with `@EntityGraph`?
     * <p>
     * While you might not always need a `@Query` annotation with `@EntityGraph`, it's necessary
     * in this specific case because we've created a method with a **custom name** (`findAllWithAddresses`).
     * <p>
     * Spring Data JPA automatically understands what to do with built-in methods like `findAll()` or
     * methods that follow standard naming conventions like `findByEmail()`. However, when you create
     * a custom method name that doesn't follow these patterns, you must explicitly tell JPA what to select.
     * <p>
     * - **`@Query("select u from User u")`**: This tells JPA the base query to execute—to select all User entities.
     * <p>
     * - **`@EntityGraph(attributePaths = "addresses")`**: This then modifies that query, telling JPA to
     * also eagerly fetch the `addresses` collection for each User in a single, optimized `JOIN` query.
     * <p>
     * In short, the `@Query` defines **what** to fetch, and the `@EntityGraph` defines **how** to fetch its
     * related data to solve the N+1 problem.
     */

    @EntityGraph(attributePaths = "addresses")
    @Query("select u from User u")
    List<User> findAllWithAddresses();

    List<User> findByProfile(Profile profile);

    @EntityGraph(attributePaths = "profile")
    @Query("select u.id as id, u.email as email from User u " +
            "join Profile p on u.id = p.user.id " +
            "where p.loyaltyPoints > :value " +
            "order by u.email")
    List<UserSummary> findByLoyaltyPointsGreaterThan(@Param("value") int value);

}