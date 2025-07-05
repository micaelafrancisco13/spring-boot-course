package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.User;
import com.example.storespringdatajpa.repositories.ProfileRepository;
import com.example.storespringdatajpa.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    // this EntityManager is responsible for managing entities using a persistence context
    private final EntityManager em;

    @Transactional
    public void showEntityStates() {
        // these 2 transactions from userRepository: findById() and delete() have their
        // own persistence context. while the transaction is in progress, we have a
        // persistence context.

        // what if we want to tie a transaction boundary not only in the repository methods
        // (e.g., save()) but inside a n entire method for multiple transactions?
        // use the method-level @Transactional annotation

        var user = new User();
        user.setName("Ela");
        user.setEmail("ela13@gmail.com");
        user.setPassword("Password13!");

        // check if the object is already on the persistence context
        if (em.contains(user)) System.out.println("Persistent");
        else System.out.println("Transient or Detached");

        // all the methods of a repository are transactional
        userRepository.save(user);
        userRepository.findAll().forEach(u -> System.out.println(u.getEmail()));

        if (em.contains(user)) System.out.println("Persistent");
        else System.out.println("Transient or Detached");

    }

    @Transactional // this is the key!
    public void showRelatedEntities() {
        var profile = profileRepository
                .findById(UUID.fromString("5beed2b0-49a8-4f77-91c6-81f391d00ebc"))
                .orElseThrow(() -> new IllegalArgumentException("Profile not found"));
        System.out.println("Profile: " + profile.getUser());

        // This throws a LazyInitializationException
         System.out.println("Profile: " + profile.getUser().getName());

        // Why?
        // 1. `@OneToOne(fetch = FetchType.LAZY)`: Tells Hibernate to load `User` as a proxy,
        // not immediately fetch its data, when `Profile` is retrieved.
        // 2. `profileRepository.findById()`: Fetches `Profile` data. A Hibernate session
        // is active during this call, and a `User` proxy is placed in `profile.user`.
        // 3. **Session Closure:** If the method containing `findById()` (and the subsequent
        // access to the lazy association) is *not* within a `@Transactional` boundary,
        // the Hibernate session closes immediately after `findById()` completes.
        // 4. `profile.getUser().getName()`: When this line executes, the `User` proxy tries
        // to fetch the real `User` data from the database. Since the session is closed,
        // it fails, throwing `LazyInitializationException`.
    }
}
