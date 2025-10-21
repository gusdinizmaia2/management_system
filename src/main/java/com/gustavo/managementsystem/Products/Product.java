package com.gustavo.managementsystem.Products;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.gustavo.managementsystem.InventoryMovements.InventoryMovement;
import com.gustavo.managementsystem.Suppliers.Supplier;
import com.gustavo.managementsystem.Users.User;

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
    private User supplier;

    @OneToMany(mappedBy = "product")
    private List<InventoryMovement> inventory_movements;

    public User getSupplier(){
        return this.supplier;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void setDescription(String description){
        this.description = description;
    }

}
