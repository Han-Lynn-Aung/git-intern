package com.cdsg.gitintern.controller;

import com.cdsg.gitintern.model.Category;
import com.cdsg.gitintern.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<?> getCategoryIdOrName(@PathVariable String identifier){
        try{
            long id = Long.parseLong(identifier);
            Optional<Category> categoryById = categoryService.findCategoryById(id);
            if (categoryById.isPresent()){
                return ResponseEntity.ok(categoryById.get());
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found by id : " + id);
            }
        }catch (NumberFormatException e){
            Optional<Category> categoryByName = categoryService.findCategoryByName(identifier);
            if(categoryByName.isPresent()){
                return ResponseEntity.ok(categoryByName.get());
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found by name : " + identifier);
            }
        }
    }

}
