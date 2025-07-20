package com.bhanu.ecommerce_backend.Service;

import com.bhanu.ecommerce_backend.model.Category;
import com.bhanu.ecommerce_backend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public String addCategory(Category category) {
        categoryRepository.save(category);
        return "Category added successfully";
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
