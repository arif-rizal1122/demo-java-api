package com.demo_api.demo_api.services;

import com.demo_api.demo_api.models.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo_api.demo_api.models.repository.CategoryRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    
     @Autowired
     private CategoryRepo categoryRepo;

     public Category save(Category category){
        return categoryRepo.save(category);
     }


     public Category findOneById(Long id) {
        return categoryRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Category Not Found!!!"));
    }

    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }
    
    public void removeOne(Long id){
        categoryRepo.deleteById(id);
    }


}
