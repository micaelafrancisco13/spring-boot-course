package com.example.storespringdatajpa;

import com.example.storespringdatajpa.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class StoreSpringDataJpaApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(StoreSpringDataJpaApplication.class, args);
        var service = context.getBean(ProductService.class);
        service.fetchProductsBySpecification("iphone", BigDecimal.valueOf(125_000), null);
    }
}