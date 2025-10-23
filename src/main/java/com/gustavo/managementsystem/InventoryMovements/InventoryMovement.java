package com.gustavo.managementsystem.InventoryMovements;

import lombok.Data;
import jakarta.persistence.*;
import com.gustavo.managementsystem.Products.Product;
import com.gustavo.managementsystem.Users.User;


@Data
@Entity(name= "inventory_movements")
public class InventoryMovement{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private InventoryMovementTypes type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String date;

    @ManyToOne
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id", unique=false)
    private User owner_user;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    }