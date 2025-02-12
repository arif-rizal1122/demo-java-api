package com.demo_api.demo_api.services;

import com.demo_api.demo_api.models.entity.Supplier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo_api.demo_api.models.repository.SupplierRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {
    
    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }


    public Supplier findOneById(Long id){
        return supplierRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Supplier not found"));
    }

    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }

    public Supplier findByEmail(String email){
         return supplierRepo.findByEmail(email);
    }


    public List<Supplier> findByName(String name){
        return supplierRepo.findByNameContains(name);
    }

    public List<Supplier> findByNameStarWith(String name){
        return supplierRepo.findByNameStartingWith(name);
    }


    public List<Supplier> findByNameOrEmail(String name, String email){
        return supplierRepo.findByNameContainsOrEmailContains(name, email);
    }


}
