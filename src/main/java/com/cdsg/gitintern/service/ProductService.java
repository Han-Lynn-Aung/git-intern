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
        Optional<Product> getSingleProduct = prodRepository.findById(id);
    if(getSingleProduct.isPresent()){
        return getSingleProduct;
    }
        throw new RuntimeException("Product not available" + id);
    }

    public Product updateProduct(Product product) {
        Optional<Product> productUpdate = prodRepository.findById(product.getId());
        if (productUpdate.isPresent()){
            Product updateProd = productUpdate.get();
            updateProd.setName(product.getName());
            updateProd.setDescription(product.getDescription());
            updateProd.setPrice(product.getPrice());
            prodRepository.save(updateProd);
            return updateProd;
        }
        throw new RuntimeException("Product not available" + product.getId());
    }
    public void deleteProduct(Long id) {
        prodRepository.deleteById(id);
    }


}
