package com.gustavo.managementsystem.Suppliers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAllSuplliers(){
        return supplierRepository.findAll();
    }

}
