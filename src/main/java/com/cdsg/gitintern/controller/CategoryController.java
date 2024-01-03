package com.cdsg.gitintern.controller;

import com.cdsg.gitintern.exception.categoryexception.CategoryConflictException;
import com.cdsg.gitintern.exception.categoryexception.CategoryNotFoundException;
import com.cdsg.gitintern.exception.categoryexception.InvalidCategoryException;
import com.cdsg.gitintern.model.Category;
import com.cdsg.gitintern.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        logger.info("Retrieving all categories");
        List<Category> categories = categoryService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getCategoryByIdOrName(@PathVariable String identifier) {
        try {
            long id = Long.parseLong(identifier);
            Optional<Category> categoryById = categoryService.findCategoryById(id);

            if (categoryById.isPresent()) {
                logger.info("Category found by ID: {}", id);
                return ResponseEntity.ok(categoryById.get());
            } else {
                logger.error("Category not found by ID: {}", id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found by ID: " + id);
            }
        } catch (NumberFormatException e) {
            Optional<Category> categoryByName = categoryService.findCategoryByName(identifier);

            if (categoryByName.isPresent()) {
                logger.info("Category found by name: {}", identifier);
                return ResponseEntity.ok(categoryByName.get());
            } else {
                logger.error("Category not found by name: {}", identifier);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found by name: " + identifier);
            }
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        try{
            logger.info("Received request to create category: {}" , category );
            validateCategoryData(category);
            Category savedCategory = categoryService.saveCategory(category);
            logger.info("Created category: {}", savedCategory);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
        }catch (InvalidCategoryException e){
            logger.error("Failed to create due to invalid category data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,@RequestBody Category updateCategory){
        try{
            logger.info("Received request to update category with ID {} : {}",id,updateCategory);
            validateCategoryData(updateCategory);
            Category updatedCategory = categoryService.updateCategory(id,updateCategory);
            logger.info("Updated category: {}",updatedCategory);
            return ResponseEntity.ok(updatedCategory);
        }catch (CategoryNotFoundException e) {
            logger.error("Failed to update category as it was not found: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Long id){
        try{
            logger.info("Received request to delete category with ID: {}",id );
           Optional<Category> existingCategory = categoryService.findCategoryById(id);
            if(existingCategory.isPresent()){
                categoryService.deleteCategoryById(id);
            }
            logger.info("Deleted category with ID {}", id);
            return ResponseEntity.ok("Deleted Category");
        }catch (CategoryNotFoundException e){
            logger.error("Failed to delete category as it was not found :{}",e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    private void validateCategoryData(Category category) {
        if(category.getName() == null){
            throw new InvalidCategoryException("Invalid category data : Name can not null");
        }
        Optional<Category> existingCategory = categoryService.findCategoryByName(category.getName());

        if (existingCategory.isPresent()) {
            throw new CategoryConflictException("Category with the same name already exists");
        }
    }
}
