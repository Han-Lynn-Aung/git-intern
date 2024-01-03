package com.cdsg.gitintern.service;

import com.cdsg.gitintern.model.Product;
import com.cdsg.gitintern.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository prodRepository;

    public Product createNewProduct(Product product) {
        return prodRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return prodRepository.findAll();
    }

}
