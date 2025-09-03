package com.gustavo.managementsystem.InventoryMovements;

import java.lang.annotation.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class InventoryMovementController{

    @Autowired
    private InventoryMovementService inventoryMovementService;

    @GetMapping("/inventory_repository")
    public List<InventoryMovement> getAllInventoryMovements(){
        return inventoryMovementService.findAllInventoryMovements();
    }
}