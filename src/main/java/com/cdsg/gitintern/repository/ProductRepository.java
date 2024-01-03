package com.cdsg.gitintern.repository;

import com.cdsg.gitintern.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findAll();

}
