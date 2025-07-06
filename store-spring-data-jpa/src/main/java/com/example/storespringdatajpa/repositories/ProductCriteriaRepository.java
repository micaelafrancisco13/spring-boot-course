package com.example.storespringdatajpa.repositories;

import com.example.storespringdatajpa.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> findProductsByCriteria(String name, BigDecimal min, BigDecimal max);
}
