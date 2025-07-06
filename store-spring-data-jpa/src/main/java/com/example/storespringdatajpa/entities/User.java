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
//@ToString - has a warning
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

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private Set<Address> addresses = new HashSet<>();

    // What if you want to load a user along with its associated tags?
    // One way is to change the fetch strategy to EAGER loading, but that comes with a trade-off:
    // the tags will be loaded every time you fetch a user — even if you don’t need them.
    //
    // If you only want to load the tags for specific queries,
    // a better approach is to keep the fetch type as LAZY (the default for @ManyToMany)
    // and use an @EntityGraph in those targeted queries.
    // This allows you to control when the related tags are loaded, optimizing performance.
    // Go to the UserRepository.
    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Profile profile;

    @ManyToMany
    @JoinTable(
            name = "wishlists",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
