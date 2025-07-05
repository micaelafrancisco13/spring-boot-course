package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.User;
import com.example.storespringdatajpa.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

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

        if (em.contains(user)) System.out.println("Persistent");
        else System.out.println("Transient or Detached");

//        userRepository.delete(user);
    }
}
