package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}