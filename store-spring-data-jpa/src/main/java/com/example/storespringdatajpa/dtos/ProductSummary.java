package com.example.storespringdatajpa.dtos;

import java.util.UUID;

/**
 * An Interface-Based Projection for fetching a subset of Product fields.
 * Spring Data JPA will automatically generate a proxy implementation for this interface.
 */
public interface ProductSummary {
    UUID getId();
    String getName();
}