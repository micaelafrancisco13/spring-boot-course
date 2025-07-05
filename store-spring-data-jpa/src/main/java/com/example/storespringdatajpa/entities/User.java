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

    // CascadeType determines how operations performed on the parent entity (the User, in
    // this case) are automatically applied or "cascaded" to its associated child entities
    // (Address).
    // - CascadeType.PERSIST: When you save (persist) a User, any new Address entities
    //   added to this 'addresses' set will also be automatically saved to the database.
    // - CascadeType.REMOVE: When you delete (remove) a User, all associated Address entities
    //   in this set will also be automatically deleted from the database. This helps maintain
    //   data integrity by preventing orphaned address records.
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Address> addresses = new HashSet<>();

    // JoinColumn many-to-one
    // JoinTable many to many
    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    /*
     * ===================================================================================
     * CRITICAL FIX: The Importance of CascadeType.REMOVE on the Profile Relationship
     * ===================================================================================
     *
     * This was the primary reason the delete operation was failing. This is an
     * **application-level block**, not a database one.
     *
     * WHY IT'S ESSENTIAL:
     * Without `cascade = CascadeType.REMOVE`, we gave Hibernate (our JPA provider)
     * conflicting rules:
     * 1. "Delete the User."
     * 2. "But do NOT touch the Profile it's linked to."
     *
     * Hibernate's main job is to keep its in-memory cache of objects (the "persistence context")
     * consistent. To prevent creating an "orphaned" Profile object in its memory, it would
     * stop the entire transaction immediately.
     *
     * THE SOLUTION:
     * By adding `cascade = CascadeType.REMOVE`, we give Hibernate clear permission: "When a User
     * is deleted, its associated Profile must be deleted too." This resolves the logical
     * conflict, allowing Hibernate to correctly manage the object's lifecycle and proceed
     * with the deletion.
     *
     */
    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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
