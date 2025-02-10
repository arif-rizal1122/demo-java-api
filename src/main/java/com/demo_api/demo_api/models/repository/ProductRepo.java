package com.demo_api.demo_api.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo_api.demo_api.models.entity.Product;
import java.util.List;




public interface ProductRepo extends CrudRepository<Product, Long> {
    
    List<Product> findByNameContains(String name);
    

} 
