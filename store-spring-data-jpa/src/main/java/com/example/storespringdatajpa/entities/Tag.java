package com.example.storespringdatajpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "tags") // the name of the field that owns the relationship
    @ToString.Exclude
    private Set<User> users = new HashSet<>();

    public Tag(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
