package com.demo_api.demo_api.models.repository;

import com.demo_api.demo_api.models.entity.Supplier;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    
     Supplier findByEmail(String email);

     List<Supplier> findByNameContains(String name);

     List<Supplier> findByNameStartingWith(String name);

     List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
