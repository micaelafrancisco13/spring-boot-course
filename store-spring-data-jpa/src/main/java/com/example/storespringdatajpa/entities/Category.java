package com.example.storespringdatajpa.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    // the category field in the Product entity is the
    // owner of the relationship
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
