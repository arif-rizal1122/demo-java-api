package com.demo_api.demo_api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo_api.demo_api.models.entity.Product;
import com.demo_api.demo_api.services.ProductService;
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
    public Product create(@RequestBody Product product){
        return productService.save(product);
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
    public Product update(@PathVariable("productId") Long id, @RequestBody Product product){
        return productService.update(id, product);
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
