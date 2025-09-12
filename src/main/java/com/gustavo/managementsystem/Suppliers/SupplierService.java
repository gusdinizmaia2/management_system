package com.gustavo.managementsystem.Suppliers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAllSuplliers(){
        return supplierRepository.findAll();
    }

}
