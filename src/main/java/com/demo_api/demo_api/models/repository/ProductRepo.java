package com.demo_api.demo_api.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo_api.demo_api.models.entity.Product;
import com.demo_api.demo_api.models.entity.Supplier;

import java.util.List;




public interface ProductRepo extends CrudRepository<Product, Long> {
    
    List<Product> findByNameContains(String name);
    
    @Query("SELECT p FROM Product p WHERE p.name LIKE :name")
    List<Product> findProductByName(@Param("name") String name);
    
    @Query("SELECT p FROM Product p WHERE p.category.id= :categoryId")
    public List<Product> findProductByCategory(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@Param("supplier") Supplier supplier);
    
} 
