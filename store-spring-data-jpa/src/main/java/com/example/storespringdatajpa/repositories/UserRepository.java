package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}