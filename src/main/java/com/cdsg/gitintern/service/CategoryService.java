package com.cdsg.gitintern.service;

import com.cdsg.gitintern.exception.categoryexception.CategoryNotFoundException;
import com.cdsg.gitintern.exception.categoryexception.InvalidCategoryException;
import com.cdsg.gitintern.model.Category;
import com.cdsg.gitintern.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public Category saveCategory(Category category) {
        if (category.getName() == null) {
            throw new InvalidCategoryException("Invalid Category Data : Name can not be null!");
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category updateCategory) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with ID " + id + " not found"));
        existingCategory.setName(updateCategory.getName());
        return categoryRepository.save(existingCategory);
    }

    public void deleteCategoryById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundException("Category with ID " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }
}
