package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.Product;
import com.example.storespringdatajpa.repositories.CategoryRepository;
import com.example.storespringdatajpa.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.query.IllegalQueryOperationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashSet;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public void fetchAllProductsInAGivenPriceRange() {
        var products = productRepository
                .findProducts(BigDecimal.valueOf(100_000), BigDecimal.valueOf(150_000));
        products.forEach(p -> System.out.println("Product name: " + p.getName()));
    }

    @Transactional
    public void addSampleProducts() {
        var products = new LinkedHashSet<Product>();

        var product1 = new Product();
        product1.setName("MacBook Pro");
        product1.setDescription("A laptop from Apple");
        product1.setPrice(BigDecimal.valueOf(100_000));

        var product2 = new Product();
        product2.setName("iPhone 13");
        product2.setDescription("A smartphone from Apple");
        product2.setPrice(BigDecimal.valueOf(1000));

        var product3 = new Product();
        product3.setName("Samsung Galaxy S21 Ultra");
        product3.setDescription("A smartphone from Samsung");
        product3.setPrice(BigDecimal.valueOf(10000));

        var categoryName = categoryService.addSampleCategories();

        var categoryFound = categoryRepository.findByName(categoryName);

        product1.setCategory(categoryFound);
        product2.setCategory(categoryFound);
        product3.setCategory(categoryFound);

        products.add(product1);
        products.add(product2);
        products.add(product3);

        productRepository.saveAll(products);
    }

    public void deleteProduct() {
        var productFound = productRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new IllegalQueryOperationException("Product not found"));

        productRepository.delete(productFound);

    }
}
