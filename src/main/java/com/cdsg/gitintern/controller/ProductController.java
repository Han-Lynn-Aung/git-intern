package com.cdsg.gitintern.controller;

import com.cdsg.gitintern.model.Product;
import com.cdsg.gitintern.repository.ProductRepository;
import com.cdsg.gitintern.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService prodService;
    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(prodService.getAllProduct());
    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<Optional<Product>> getProductById(@PathVariable Long id) {
//        Optional<Product> product = prodService.getProductById(id);
//
//        if (product.isPresent()) {
//            return ResponseEntity.ok(product);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
@GetMapping("/get/{id}")
public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Optional<Product> product = prodService.getProductById(id);

    return product.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
}



    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(prodService.createNewProduct(product));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return ResponseEntity.ok(prodService.updateProduct(product));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        prodService.deleteProduct(id);
        return ResponseEntity.ok(true);
    }
}
