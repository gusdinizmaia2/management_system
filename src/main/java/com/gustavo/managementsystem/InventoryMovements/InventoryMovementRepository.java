package com.gustavo.managementsystem.InventoryMovements;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    //Optional<InventoryMovement> findById(Long id);
}
