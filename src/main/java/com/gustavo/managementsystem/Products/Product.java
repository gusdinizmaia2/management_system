package com.gustavo.managementsystem.Products;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.gustavo.managementsystem.InventoryMovements.InventoryMovement;
import com.gustavo.managementsystem.Suppliers.Supplier;

@Data
@Entity(name="products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<InventoryMovement> inventory_movements;

}
