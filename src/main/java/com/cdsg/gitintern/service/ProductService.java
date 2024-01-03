package com.cdsg.gitintern.service;

import com.cdsg.gitintern.model.Product;
import com.cdsg.gitintern.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository prodRepository;

    public ProductService(ProductRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    public Product createNewProduct(Product product) {
        return prodRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return prodRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return prodRepository.findById(id);
    }

    public Product updateProduct(Product product) {
        return prodRepository.save(product);
    }
    public void deleteProduct(Long id) {
        prodRepository.deleteById(id);
    }


}
