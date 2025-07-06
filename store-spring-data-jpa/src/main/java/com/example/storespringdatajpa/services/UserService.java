package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * WARNING: This method demonstrates the N+1 select problem, a classic issue
     * caused directly by lazy loading.
     * <p>
     * ### How it Happens
     * <p>
     * 1.  **Lazy Loading**: In the `User` entity, the `@OneToMany` relationship to `Address`
     * is **LAZY** by default. This is an optimization that tells Hibernate: "Don't load
     * a user's addresses until they are explicitly asked for."
     * <p>
     * 2.  **The "1" Query**: The first line, `userRepository.findAll()`, executes a
     * single query to get all 'N' users. Because of lazy loading, this query only
     * fetches data from the `users` table. The `addresses` collection in each `User`
     * object is just a placeholder (a proxy).
     * <p>
     * `SELECT * FROM users;`
     * <p>
     * 3.  **The "N" Queries**: The code then loops through each user. The moment `u.getAddresses()`
     * is called, it triggers the lazy loading mechanism. Hibernate sees that the addresses
     * for that specific user haven't been loaded, so it must execute a *new*, separate
     * query to fetch them. This happens for every single user in the loop.
     * <p>
     * `SELECT * FROM addresses WHERE user_id = ?;` // This query runs 'N' times
     * <p>
     * ---
     * <p>
     * ### The Result
     * <p>
     * You end up with **1** initial query for the users + **N** additional queries (one for each
     * user's addresses), leading to a total of **N+1** database trips. This is highly
     * inefficient and can severely degrade application performance.
     * <p>
     * ---
     * <p>
     * ### The Solution: @EntityGraph 🚀
     */
    @Transactional
    public void fetchAllUsersWithAddress() {
        var users = userRepository.findAllWithAddresses();
        users.forEach(u -> {
            System.out.println("User name: " + u.getName());
            System.out.print('\n');
            // If the repository uses @EntityGraph, this line no longer triggers a query
            u.getAddresses().forEach(a -> System.out.println("User address: " + a));
        });
    }
}
