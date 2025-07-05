package com.example.storespringdatajpa.services;

import com.example.storespringdatajpa.entities.Category;
import com.example.storespringdatajpa.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public String addSampleCategories() {
        var category = new Category();
        category.setName("Electronics");
        categoryRepository.save(category);

        return category.getName();
    }
}
