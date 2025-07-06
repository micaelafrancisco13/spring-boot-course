package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    // The attributePaths property specifies the names of the related entities to load eagerly.
    @EntityGraph(attributePaths = {"addresses"})
    Optional<User> findByEmail(String email);

    /**
     * ## Lazy Loading vs. @EntityGraph: A Quick Guide 🚀
     *
     * This comment explains the key differences between default lazy loading in "many" relationships
     * and using an `@EntityGraph` for data fetching in JPA and Spring Boot.
     *
     * ### What is Lazy Loading?
     *
     * By default, `@OneToMany` and `@ManyToMany` relationships are **LAZY** loaded. This is a performance
     * optimization strategy.
     *
     * - **How it works**: When you fetch a parent entity (e.g., a `User`), its collection of related entities
     * (e.g., `posts` or `comments`) is not immediately loaded from the database. Instead, JPA uses a
     * proxy object for the collection.
     * - **When it loads**: The actual data for the collection is only fetched from the database when you
     * explicitly access it for the first time (e.g., by calling `user.getPosts()`).
     * - **The "N+1 Select" Problem**: The major drawback of lazy loading is the "N+1 select" problem. If you
     * fetch a list of `N` users and then loop through them to get their posts, you will execute one initial
     * query for the users and `N` additional queries for each user's posts. This results in `N+1` total
     * database queries, which can severely impact performance.
     *
     * ### What is the @EntityGraph?
     *
     * An `@EntityGraph` provides a more fine-grained and dynamic approach to data fetching. It allows you to
     * specify which related entities should be loaded eagerly for a specific query, overriding the default
     * lazy behavior.
     *
     * - **How it works**: You define an `@EntityGraph` on a repository method and specify the `attributePaths`
     * (the names of the related fields) that you want to load along with the main entity.
     * - **Performance**: When the repository method is called, JPA generates a single, optimized SQL query
     * (typically using a `JOIN`) to fetch the main entity and all the specified related entities at once.
     * - **Solves the "N+1" Problem**: By fetching all the required data in one go, `@EntityGraph` effectively
     * eliminates the "N+1 select" problem for that specific operation.
     */

    /**
     * ## @EntityGraph vs. FetchType.EAGER: What's the Difference?
     *
     * You might ask: "If @EntityGraph performs eager loading, why not just set the relationship
     * to `fetch = FetchType.EAGER` on the entity itself?"
     *
     * The key difference is **Control vs. Convention**.
     *
     * ---
     *
     * ### `fetch = FetchType.EAGER`
     *
     * This is a **static, all-or-nothing** instruction on the entity itself.
     *
     * - **How it works**: When you define `@OneToMany(fetch = FetchType.EAGER)`, you are telling JPA:
     * "*Every single time* you load this entity, you *must also* load this collection."
     * - **The Problem**: This is often inefficient. If you have a screen that just displays a list of
     * Usernames, you are still forced to run a complex `JOIN` and fetch all the `Addresses`, `Tags`,
     * and `Orders` for every user, even though you don't need them. This leads to slow queries and
     * high memory usage for no benefit. It's a major cause of performance issues.
     *
     * ### `@EntityGraph`
     *
     * This is a **dynamic, per-query** instruction on a repository method.
     *
     * - **How it works**: You keep the default `FetchType.LAZY` on your entity, which is the best practice.
     * Then, for a *specific operation* where you know you'll need the related data (e.g., "getUserProfileAndAddresses"),
     * you add an `@EntityGraph` to that repository method.
     * - **The Benefit**: This gives you fine-grained control. JPA will only perform the eager-loading `JOIN` for
     * that one specific query. All other queries that load the entity will continue to use efficient, lazy loading.
     * This allows you to create an optimized data-fetching plan for each specific use case.
     *

     * **Conclusion**: Always default your `@OneToMany` and `@ManyToMany` relationships to `FetchType.LAZY`.
     * When you need to prevent an N+1 query problem for a specific operation, use `@EntityGraph` on your
     * repository method to selectively eager-load the data you need.
     */
}