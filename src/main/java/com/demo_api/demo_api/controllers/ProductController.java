package com.demo_api.demo_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo_api.demo_api.dto.ResponseData;
import com.demo_api.demo_api.helpers.ValidationHelper;
import com.demo_api.demo_api.models.entity.Product;
import com.demo_api.demo_api.services.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;



    @PostMapping("/create")
    public ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, BindingResult result){
      ResponseData<Product> responseData = new ResponseData<>();  
      ResponseEntity<ResponseData<Product>> validationResponse = ValidationHelper.validateRequest(result);
      if (validationResponse != null) {
       return validationResponse;
      } 
      responseData.setStatus(true);
      responseData.setPayload(productService.save(product));
      return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
    }



    @GetMapping("/all")  
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/getById/{productId}") 
    public Product findOne(@PathVariable("productId") Long id){
        return productService.findOne(id);
    }



@PutMapping("update/{productId}")
public ResponseEntity<ResponseData<Product>> update(
        @PathVariable("productId") Long id, 
        @Valid @RequestBody Product product, 
        BindingResult result) {

       ResponseData<Product> responseData = new ResponseData<>();
       
       ResponseEntity<ResponseData<Product>> validationResponse = ValidationHelper.validateRequest(result);
       if (validationResponse != null) {
        return validationResponse;
       }
       // cek id tidal sama dengan yg ada di params
       if (!productService.existById(id)) {
           responseData.setStatus(false);
           responseData.getMessages().add("Product id " + id + " not found");
           responseData.setPayload(null);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
       }
       // ambil data lama
       Product existProduct = productService.findOne(id);
       existProduct.setName(product.getName());
       existProduct.setPrice(product.getPrice());
       existProduct.setDescription(product.getDescription());
       // masukan / timpa ke data baru
       Product updateProduct = productService.save(existProduct);
       responseData.setStatus(true);
       responseData.setPayload(updateProduct);
       return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }


   @DeleteMapping("delete/{productId}")
    public ResponseEntity<String> removeOne(@PathVariable("productId") Long productId) {
        if (!productService.existById(productId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product with ID " + productId + " not found.");
        }
        
        productService.removeOne(productId);
        return ResponseEntity.ok("Product with ID " + productId + " successfully deleted.");
    }


}
