package com.gustavo.managementsystem.InventoryMovements;

import java.lang.annotation.*;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products/{productId}")
public class InventoryMovementController{

    @Autowired
    private InventoryMovementService inventoryMovementService;

    @PostMapping("/movement")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public InventoryMovement movementProduct(@PathVariable long productId,@Valid @RequestBody InventoryMovementCreateDTO body, Authentication authentication){
       
        var userId = authentication.getName();
       
        return inventoryMovementService.updateCountInventory(body, productId, userId);
    }

    // adicionar query params, (product and type)
    @GetMapping("/movement")
    @PreAuthorize("@authGuard.isSupplierOwner(authentication, #productId)")
    public List<InventoryMovement> getAllMovementsForSupplier(@PathVariable long productId){
        return inventoryMovementService.findAllInventoryMovements();
    }

    // adicionar query params
    @GetMapping("/movement/all")
    @PreAuthorize("@authGuard.isAdmin(authentication)")
    public List<InventoryMovement> getAllMovements(@PathVariable long productId){
        return inventoryMovementService.findAllInventoryMovements();
    }

}