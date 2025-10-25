package com.gustavo.managementsystem.InventoryMovements;

import java.lang.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping("/movements/products/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public InventoryMovement movementProduct(@PathVariable long productId,@Valid @RequestBody InventoryMovementCreateDTO body, Authentication authentication){
       
        var userId = authentication.getName();
       
        return inventoryMovementService.updateCountInventory(body, productId, userId);
    }
    
    @GetMapping("/movements/{movementId}/products/{productId}")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public InventoryMovement getMovementById(@PathVariable long productId){
        return inventoryMovementService.findMovementById(productId);
    }

    // add query params(product, type), this route is for supplier 
    @GetMapping("")
    @PreAuthorize("@authGuard.isSupplier(authentication)")
    public List<InventoryMovement> getAllMovementsForSupplier(
        @RequestParam(required = false) long productId,
        @RequestParam(required = false) int quantity,
        Authentication authentication
        ){

        String supplierId = authentication.getName();


        return inventoryMovementService.findMovementsBySupplier(productId, quantity, supplierId);
    }

    // add query params(product, type), this route is for Admin
    @GetMapping("/movements/all")
    @PreAuthorize("@authGuard.isAdmin(authentication)")
    public List<InventoryMovement> getAllMovements(
        @RequestParam(required = false) long productId,
        @RequestParam(required = false) UUID supplierId
        ){

        return inventoryMovementService.findAllMovements(productId, supplierId);
    }

}