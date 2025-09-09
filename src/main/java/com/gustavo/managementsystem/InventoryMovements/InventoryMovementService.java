package com.gustavo.managementsystem.InventoryMovements;
import java.lang.annotation.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryMovementService{

    @Autowired
    private InventoryMovementRepository inventoryMovementRepository;

    public List<InventoryMovement> findAllInventoryMovements(){
        return inventoryMovementRepository.findAll();
    }

}