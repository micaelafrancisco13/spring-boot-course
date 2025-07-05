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

    /**
     * One-to-Many relationship between User and Address.
     *
     * Key behaviors:
     * - CascadeType.PERSIST: When a User is saved, any new Address in the set will also be saved.
     * - CascadeType.REMOVE: When a User is deleted, all associated Addresses will also be deleted.
     * - orphanRemoval = true:
     *     Enables automatic deletion of orphaned Address entities (i.e., those removed from the User's address set).
     *
     * Why orphanRemoval is important here:
     * -------------------------------------------------
     * In a bidirectional relationship, the `User` entity is the inverse side (mappedBy = "user"),
     * and the `Address` entity owns the foreign key (`user_id`).
     *
     * If an Address is removed from the User's `addresses` set without also setting `address.setUser(null)`,
     * Hibernate will not consider the Address as orphaned, because it still references the User.
     *
     * By using orphanRemoval = true **and** ensuring that the Address's user reference is also set to null,
     * Hibernate will correctly interpret the Address as orphaned and issue a DELETE statement for it.
     *
     * Without orphanRemoval:
     * - Removing an Address from the set would only break the in-memory link.
     * - The Address would still remain in the database, leading to "orphan" rows.
     *
     * With orphanRemoval:
     * - Hibernate will automatically delete the Address from the database once it's
     *   removed from the set **and** its reference to the User is cleared.
     *
     * This ensures consistency between the object model and the database,
     * and prevents null constraint violations on the `user_id` column.
     */
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @ToString.Exclude
    private Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @ToString.Exclude
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
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
