package com.demo_api.demo_api.models.repository;

import com.demo_api.demo_api.models.entity.Category;

import org.springframework.data.repository.CrudRepository;



public interface CategoryRepo extends CrudRepository<Category, Long>{
    
}
