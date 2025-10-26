package com.gustavo.managementsystem.InventoryMovements;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movements")
public class InventoryMovementController{

    @Autowired
    private InventoryMovementService inventoryMovementService;

    @PostMapping("/products/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public InventoryMovement movementProduct(@PathVariable long productId,@Valid @RequestBody InventoryMovementCreateDTO body, Authentication authentication){
       
        var userId = authentication.getName();
       
        return inventoryMovementService.updateCountInventory(body, productId, userId);
    }
    
    // TA OK
    @GetMapping("/{movementId}/products/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public InventoryMovement getMovementById(@PathVariable long productId, @PathVariable long movementId){
        return inventoryMovementService.findMovementById(movementId);
    }

    // add query params(product, type), this route is for supplier 
    @GetMapping("")
    @PreAuthorize("@authGuard.isSupplier(authentication)")
    public List<InventoryMovement> getMovementsForSupplier(
        @RequestParam(required = false) String productId,
        Authentication authentication
        ){

        String supplierId = authentication.getName();


        return inventoryMovementService.findMovementsBySupplier(productId, supplierId);
    }


    @GetMapping("/all")
    @PreAuthorize("@authGuard.isAdmin(authentication)")
    public List<InventoryMovement> getAllMovements(
        @RequestParam(required = false) String productId,
        @RequestParam(required = false) String supplierId
        ){

        return inventoryMovementService.findAllMovements(productId, supplierId);
    }

}