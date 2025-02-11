package com.demo_api.demo_api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo_api.demo_api.dto.CategoryData;
import com.demo_api.demo_api.dto.ResponseData;
import com.demo_api.demo_api.helpers.ValidationHelper;
import com.demo_api.demo_api.models.entity.Category;
import com.demo_api.demo_api.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/create")
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryData categoryData, BindingResult result){
       ResponseData<Category> responseData = new ResponseData<>();
       ResponseEntity<ResponseData<Category>> validateResponse = ValidationHelper.validateRequest(result);
       if (validateResponse != null) {
           return validateResponse;
       }
       Category category = modelMapper.map(categoryData, Category.class);
       responseData.setStatus(true);
       responseData.setPayload(categoryService.save(category));
       return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }


    @GetMapping("/all")
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }


    @GetMapping("/category/{categoryId}")
    public Category findOneById(@PathVariable("categoryId") Long id){
        return categoryService.findOneById(id);
    }


    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ResponseData<Category>> update(
        @Valid @PathVariable("categoryId") Long id,
        @Valid @RequestBody CategoryData categoryData,
        BindingResult result
        ){
      
        ResponseData<Category> responseData = new ResponseData<>();
        ResponseEntity<ResponseData<Category>> validateResponse = ValidationHelper.validateRequest(result);
        if (validateResponse != null) {
            return validateResponse;
        }    
        Category existCategory = categoryService.findOneById(id);
        if ((existCategory != null)) {
            responseData.setStatus(false);
            responseData.getMessages().add("Category id " + id + " not found!!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        
        modelMapper.map(categoryData, existCategory);
        
        Category updateCategory = categoryService.save(existCategory);
        responseData.setStatus(true);
        responseData.setPayload(updateCategory);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }


}
