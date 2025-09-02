package com.gustavo.managementsystem.InventoryMovements;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity(name= "inventory_movements")
public class InventoryMovement{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String date;
}