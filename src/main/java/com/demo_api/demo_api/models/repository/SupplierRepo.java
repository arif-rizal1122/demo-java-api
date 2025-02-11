package com.demo_api.demo_api.models.repository;

import com.demo_api.demo_api.models.entity.Supplier;

import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    

}
