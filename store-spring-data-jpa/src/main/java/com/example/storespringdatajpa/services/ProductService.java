package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.Product;
import com.example.storespringdatajpa.repositories.CategoryRepository;
import com.example.storespringdatajpa.repositories.ProductRepository;
import com.example.storespringdatajpa.repositories.specifications.ProductSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.query.IllegalQueryOperationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;

@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public void fetchProductsBySpecification(String name, BigDecimal min, BigDecimal max) {
        // 1. Create a list to hold specifications based on the provided filter criteria.
        var specs = new ArrayList<Specification<Product>>();

        if (name != null) {
            specs.add(ProductSpecification.hasName(name));
        }
        if (min != null) {
            specs.add(ProductSpecification.hasPriceGreaterThanOrEqualTo(min));
        }
        if (max != null) {
            specs.add(ProductSpecification.hasPriceLessThanOrEqualTo(max));
        }

        // 2. Combine the list of specifications into a single Specification using a logical AND.
        // The reduce operation chains them together (e.g., spec1.and(spec2).and(spec3)...).
        var finalSpec = specs.stream()
                .reduce(Specification::and)
                .orElse(null); // If the list is empty, `orElse(null)` ensures the spec is null.

        // 3. Execute the query. Passing a null specification to findAll() is equivalent
        // to an unconditional query and will return all products.
        productRepository.findAll(finalSpec).forEach(p -> System.out.println(p.getName()));
    }

    public void fetchAllProducts() {
        var product = new Product();
        product.setName("iPhone");

        // LIKE operator
        var matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        productRepository.findAll(example).forEach(p -> System.out.println(p.getName()));
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
