package com.example.storespringdatajpa.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Set<Address> addresses = new HashSet<>();

    // JoinColumn many-to-one
    // JoinTable many to many
    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    // When you delete a User record, the database automatically
    // finds and removes all rows in the wishlists table where
    // the user_id matches the ID of the deleted user.
    @OnDelete(action = OnDeleteAction.CASCADE)
    // one-way relationship from user -> product
    // once we have a product object, we don't need a reference
    // to the user that has that product in his/her wishlist
    private Set<Product> wishlists = new LinkedHashSet<>();

    public void addAddress(Address address) {
        address.setUser(this);
        addresses.add(address);
    }

    public void removeAddress(Address address) {
        address.setUser(null);
        addresses.remove(address);
    }

    public void addTag(String tagName) {
        var tag = new Tag(tagName);
        tag.addUser(this);
        tags.add(tag);
    }

    public void setProfile(Profile profile) {
        profile.setUser(this);
        this.profile = profile;
    }
}
