package com.demo_api.demo_api.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo_api.demo_api.models.entity.Category;




public interface CategoryRepo extends JpaRepository<Category, Long>{
    
    Page<Category> findByNameContains(String name, Pageable pageable);


}
