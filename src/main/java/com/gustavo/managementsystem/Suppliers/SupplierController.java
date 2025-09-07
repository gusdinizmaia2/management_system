package com.gustavo.managementsystem.Suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired 
    SupplierService supplierService;    
    
    @GetMapping
    public List<Supplier> getAllSuppliers(){
        return supplierService.findAllSuplliers();
    } 

}
