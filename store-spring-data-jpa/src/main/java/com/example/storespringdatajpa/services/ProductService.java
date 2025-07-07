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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    /**
     * Fetches all products from the repository and prints their names to the console,
     * sorted by specific criteria. This method demonstrates various sorting capabilities
     * provided by Spring Data's {@link org.springframework.data.domain.Sort} class.
     */
    public void fetchSortedProducts() {
        //
        // --- Sorting Examples ---
        //
        // Example 1: Sort by multiple properties in ascending order.
        // This would sort products first by 'name' (A-Z), and then for products with the
        // same name, it would sort them by 'price' (lowest to highest).
        // Sort.by("name", "price");

        // Example 2: Sort by multiple properties in descending order.
        // This would sort all specified properties ('name' and 'price') in descending order.
        // Sort.by("name", "price").descending();

        // Example 3: Sort with mixed order (chaining conditions).
        // This creates a composite sort. It will first sort by 'name' in descending
        // order (Z-A) and then sort by 'price' in ascending order (lowest to highest).
        // The .and() method is used to combine different sort configurations.
        var sort = Sort.by("name").descending().and(Sort.by("price").ascending()); // .ascending() is default, but added for clarity.

        // Fetches all products from the productRepository using the defined 'sort' criteria.
        // It then iterates over the returned list of products and prints each product's name
        // to the standard output.
        productRepository.findAll(sort).forEach(p -> System.out.println(p.getName()));
    }

    /**
     * Fetches a "page" of products from the repository, demonstrating how to implement
     * pagination using Spring Data's {@link org.springframework.data.domain.PageRequest}
     * and {@link org.springframework.data.domain.Page} interfaces. It prints metadata
     * about the page and the names of the products on that page.
     */
    public void fetchPaginatedProducts() {
        // Defines the page number to retrieve. Pages are zero-indexed, so 0 represents the first page.
        var pageNumber = 0;

        // Defines the number of items to include in a single page. Here, we request 10 products per page.
        var pageSize = 10;

        // Creates a PageRequest object, which is Spring Data's implementation of the Pageable interface.
        // This object encapsulates the pagination parameters (page number and page size) that will be
        // passed to the repository query.
        var pageRequest = PageRequest.of(pageNumber, pageSize);

        // Executes the query to find all products but returns a 'Page' object instead of a 'List'.
        // The 'pageRequest' object tells the database to only return the subset of data corresponding
        // to the requested page (in this case, the first 10 products).
        var page = productRepository.findAll(pageRequest);

        // --- Page Metadata Output ---
        //
        // Prints the current page number. This is useful for confirming which page was retrieved.
        // Expected output: "Page number: 0"
        System.out.println("Page number: " + page.getNumber());

        // Prints the total number of products available in the entire repository, not just on the current page.
        // This is useful for calculating total pages or displaying a total count to the user.
        // Example output: "Total elements: 150"
        System.out.println("Total elements: " + page.getTotalElements());

        // Prints the total number of pages that would be required to display all elements,
        // given the current page size. It is calculated as ceil(totalElements / pageSize).
        // Example output (for 150 elements and size 10): "Total pages: 15"
        System.out.println("Total pages: " + page.getTotalPages());

        // --- Page Content Output ---
        //
        // Retrieves the actual list of products for the current page. The '.getContent()' method
        // returns a List<Product> containing the items on this specific page.
        // It then iterates over this list and prints the name of each product to the console.
        page.getContent().forEach(p -> System.out.println(p.getName()));
    }

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
