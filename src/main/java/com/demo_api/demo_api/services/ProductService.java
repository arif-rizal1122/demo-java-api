package com.demo_api.demo_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo_api.demo_api.models.entity.Product;
import com.demo_api.demo_api.models.entity.Supplier;
import com.demo_api.demo_api.models.repository.ProductRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;

    public Product  save(Product product){
        return productRepo.save(product);
    }

    public Product findOne(Long id){
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }


    public List<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }

    public Product update(Long id, Product product){
        Product productExisting = productRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Product Not Found : " + id));
  
        productExisting.setName(product.getName());
        productExisting.setDescription(product.getDescription());
        productExisting.setPrice(product.getPrice());
        return productRepo.save(productExisting);
    }

    public boolean existById(Long id){
        return productRepo.existsById(id);
    }

    public void  addSupplier(Supplier supplier, Long productId){
        Product product = findOne(productId);
        if (product == null) {
            throw new RuntimeException("Product Id" + productId + " Not Found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

    public List<Product> findByProductName(String name) {
        return productRepo.findProductByName("%"+name+"%");
    }
    

    public List<Product> findByCategory(Long categoryId){
        return productRepo.findProductByCategory(categoryId);
    }

    public List<Product> findBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOneById(supplierId);
        if (supplier==null) {
            return new ArrayList<Product>();
        }
        return productRepo.findProductBySupplier(supplier);
    }
    
}
