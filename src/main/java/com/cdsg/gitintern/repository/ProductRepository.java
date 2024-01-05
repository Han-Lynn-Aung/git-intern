package com.cdsg.gitintern.repository;

import com.cdsg.gitintern.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
