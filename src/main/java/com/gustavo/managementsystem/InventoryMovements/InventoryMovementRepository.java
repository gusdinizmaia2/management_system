package com.gustavo.managementsystem.InventoryMovements;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    //Optional<InventoryMovement> findById(Long id);3
    @Query("select mov from inventory_movements mov where mov.product.id = :product_id")
    List<InventoryMovement> findByOwnerUser_Id(UUID owner_user, 
        @Param("product_id") long productId);

    @Query("select mov from inventory_movements mov where mov.owner_user.id = :owner_user_id")
    List<InventoryMovement> findAllByOwnerUser_Id(@Param("owner_user_id") UUID ownerUserId);

    @Query("select mov from inventory_movements mov where mov.product.id = :product_id")
    List<InventoryMovement> findAllByProduct_Id(@Param("product_id") long productId);

    @Query("select mov from inventory_movements mov where mov.product.id = :product_id" +
    " and " + 
    "mov.owner_user.id = :owner_user_id")
    List<InventoryMovement> findAllByOwnerUser_IdAndProduct_Id(@Param("product_id") long productId,
        @Param("owner_user_id") UUID ownerUserId);
}
