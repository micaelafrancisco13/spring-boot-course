package com.example.storespringdatajpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Address {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "state")
    private String state;

    @ManyToOne
    @JoinColumn(name = "user_id") // instead of the @Column
    @ToString.Exclude
    private User user;

    // Q: Who's the owner of this User-Address relationship?
    // A: Address should know about a user while a user does
    // not know anything about the address.
    // We have to tell Hibernate the relationship owner

}
