package com.gustavo.managementsystem.Products;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavo.managementsystem.InventoryMovements.InventoryMovement;
import com.gustavo.managementsystem.Users.User;

@Getter
@Setter
@Entity(name="products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "supplier")
    private User supplier;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<InventoryMovement> inventory_movements;

}
