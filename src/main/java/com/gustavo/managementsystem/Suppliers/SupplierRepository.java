package com.gustavo.managementsystem.Suppliers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/suppliers")
public class SupplierRepository {

    ArrayList<SupplierModel> suppliers = new ArrayList<>();
    
    @PostMapping
    public SupplierModel createSupplier(@RequestBody SupplierModel body){

        suppliers.add(body);
        return body;
    }


    @GetMapping
    public List<SupplierModel> readAllSuppliers(){

        return suppliers;
    }

    @GetMapping("/{supplierId}")
    public Optional<SupplierModel> readSupplier(@PathVariable UUID supplierId){

        return suppliers.stream().filter(e -> e.getId().equals(supplierId)).findFirst();
    }

    @PutMapping("/{supplierId}")
    public Optional<SupplierModel> putSupplier(@PathVariable UUID supplierId, @RequestBody SupplierModel body){

        var newSuppliers = suppliers.stream().map( s -> {
            if(s.getId().equals(supplierId)){
                s.setName(body.getName());
            }
            return s;
        });

        suppliers = new ArrayList<>(newSuppliers.toList());

        return suppliers.stream().filter(supplier -> supplier.getId().equals(supplierId)).findFirst();
    }

    @DeleteMapping("/{supplierId}")
    public void deleteSupplier(@PathVariable UUID supplierId){

        var newSuppliers = suppliers.stream().filter( s -> !s.getId().equals(supplierId));

        suppliers = new ArrayList<>(newSuppliers.toList());
    }

}
