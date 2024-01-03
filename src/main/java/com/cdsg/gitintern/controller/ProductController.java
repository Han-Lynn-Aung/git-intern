package com.cdsg.gitintern.controller;

import com.cdsg.gitintern.model.Product;
import com.cdsg.gitintern.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService prodService;

    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(prodService.getAllProduct());
    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(prodService.createNewProduct(product));
    }

}
