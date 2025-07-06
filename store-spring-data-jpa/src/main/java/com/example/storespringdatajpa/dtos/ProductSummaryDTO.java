package com.example.storespringdatajpa.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

/**
 * A Class-Based Projection (DTO) for fetching a subset of Product fields.
 * It's a standard data container class. Spring Data populates it via its constructor.
 */
@AllArgsConstructor
@Getter
public class ProductSummaryDTO {
    private UUID id;
    private String name;
}