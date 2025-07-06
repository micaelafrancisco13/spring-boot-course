package com.example.storespringdatajpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Profile {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "loyalty_points")
    private int loyaltyPoints;

    // If you had FetchType.EAGER (or omitted fetch entirely, as it's the default for @OneToOne),
    // whenever you load a Profile entity from the database, Hibernate would immediately also
    // load the associated User entity in the same database query.

    // With FetchType.LAZY, when you load a Profile entity, Hibernate will NOT immediately load
    // the associated User entity. Instead, it creates a proxy for the User object.
    // The actual User data will only be fetched from the database when you first try to access
    // a method or field on that user proxy. For example, when you call
    // profile.getUser().getFirstName().
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId // Tells Hibernate that this column is both the primary key and the foreign key
    private User user;
}
