package com.example.storespringdatajpa.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private String dateOfBirth;

    @Column(name = "loyalty_points")
    private String loyaltyPoints;

    @OneToOne
    @JoinColumn(name = "id")
    @MapsId // Tells Hibernate that this column is both the primary key and the foreign key
    @ToString.Exclude
    private User user;
}
