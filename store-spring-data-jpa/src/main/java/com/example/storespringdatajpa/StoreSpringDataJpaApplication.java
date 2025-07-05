package com.example.storespringdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreSpringDataJpaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(StoreSpringDataJpaApplication.class, args);
        /**
         * Understanding Entity States in Spring Data JPA/Hibernate
         *
         * Repositories greatly simplify database interactions by abstracting away much of the underlying complexity.
         * However, a fundamental understanding of how entities are managed "under the hood" is crucial,
         * especially when debugging or optimizing. This comment explains the lifecycle of an entity
         * within Hibernate's persistence context.
         *
         * 1. **Transient State:**
         * When an entity is first instantiated using the `new` keyword (e.g., `new User()`), it is in a
         * **transient** state. At this point, the entity is a plain Java object, not associated with
         * any database or Hibernate session. It has no persistent representation and no assigned database ID.
         *
         * 2. **Persistent State:**
         * When you call the `save()` method (or `persist()`, `merge()`) on a Spring Data JPA repository
         * (e.g., `userRepository.save(user)`), the entity transitions from transient to **persistent** state.
         * At this stage, the entity is added to Hibernate's internal container called the **Persistence Context**.
         * The persistence context acts as Hibernate's working memory, tracking and managing entity instances.
         * Hibernate will then perform an INSERT operation to save the entity's data into the database.
         * Upon successful insertion, the database assigns an ID, and Hibernate updates the entity object
         * in the persistence context with this new ID.
         *
         * The persistence context is analogous to Spring's Application Context, but specifically for
         * database-managed objects (entities).
         *
         * 3. **Detached State:**
         * The persistence context has a defined lifetime, typically tied to a database transaction.
         * Once the transaction completes (e.g., the method exits, or the session is closed), the
         * persistence context is cleared. Any entities that were previously in the persistent state
         * and were managed by this persistence context now transition to a **detached** state.
         *
         * A detached entity is no longer managed or tracked by Hibernate. However, unlike a transient entity,
         * a detached entity *does* have a database identifier because it was once persistent.
         *
         * If you make changes to a detached entity and then call `save()` (or `merge()`) on the repository again,
         * Hibernate will re-attach the entity to the current persistence context, transitioning it back to
         * the persistent state. Hibernate will then detect the changes and perform an UPDATE operation
         * on the database.
         *
         * 4. **Removed State:**
         * If you call the `delete()` method on a persistent entity (e.g., `userRepository.delete(user)`),
         * the entity transitions to the **removed** state. Hibernate will then perform a DELETE operation
         * to remove the entity's data from the database.
         * After the deletion, the entity effectively becomes transient again, as it no longer has a
         * corresponding record in the database.
         *
         * This lifecycle is intricately linked to the concept of **transactions**, which define the scope
         * and lifetime of the persistence context. We will delve into transactions next.
         */
    }
}
