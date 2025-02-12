package com.demo_api.demo_api.controllers;

import com.demo_api.demo_api.models.entity.Supplier;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.demo_api.demo_api.dto.ResponseData;
import com.demo_api.demo_api.dto.SearchData;
import com.demo_api.demo_api.dto.SupplierData;
import com.demo_api.demo_api.helpers.ValidationHelper;
import com.demo_api.demo_api.services.SupplierService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
     @Autowired
     private SupplierService supplierService;
     
     @Autowired
     private ModelMapper modelMapper;

     @PostMapping("/create")
     public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierData supplierData, BindingResult result){
         ResponseData<Supplier> responseData = new ResponseData<>();
         ResponseEntity<ResponseData<Supplier>> validateResponse = ValidationHelper.validateRequest(result);
         if (validateResponse != null) {
            return validateResponse;
         }
         Supplier supplier2 = modelMapper.map(supplierData, Supplier.class);
        
         responseData.setStatus(true);
         responseData.setPayload(supplierService.save(supplier2));
         return ResponseEntity.status(HttpStatus.CREATED).body(responseData);
     }


     @GetMapping("/all")
     public Iterable<Supplier> findAll(){
        return supplierService.findAll();
     }

     @GetMapping("/supplier/{supplierId}")
     public Supplier findOneById(@PathVariable("supplierId") Long id){
        return supplierService.findOneById(id);
     }


     @PutMapping("/update/{supplierId}")
     public ResponseEntity<ResponseData<Supplier>> update(
         @Valid @PathVariable("supplierId") Long id,
         @Valid @RequestBody SupplierData supplierData, 
         BindingResult result){
         ResponseData<Supplier> responseData = new ResponseData<>();
         ResponseEntity<ResponseData<Supplier>> validateResponse = ValidationHelper.validateRequest(result);
         if (validateResponse != null) {
            return validateResponse;
         }
         
         Supplier existSupplier = supplierService.findOneById(id);
         if (existSupplier != null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Supplier id " + id + " not found!!");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
         }
         
         modelMapper.map(supplierData, existSupplier);

         Supplier updateSupplier = supplierService.save(existSupplier);
         responseData.setStatus(true);
         responseData.setPayload(updateSupplier);
         return ResponseEntity.status(HttpStatus.OK).body(responseData);
     }


     @PostMapping("/search/byEmail")
     public Supplier findByEmail(@RequestBody SearchData searchData){
          return supplierService.findByEmail(searchData.getSearchKey());
     }

     @PostMapping("/search/byName")
     public List<Supplier> findByName(@RequestBody SearchData searchData){
          return supplierService.findByName(searchData.getSearchKey());
     }


     @PostMapping("/search/nameWith")
     public List<Supplier> findByNameWith(@RequestBody SearchData searchData){
          return supplierService.findByNameStarWith(searchData.getSearchKey());
     }


     @PostMapping("/search/nameOrEmail")
     public List<Supplier> findByNameOrEmail(@RequestBody SearchData searchData){
          return supplierService.findByNameOrEmail(searchData.getSearchKey(), searchData.getOtherKey());
     }

   }
