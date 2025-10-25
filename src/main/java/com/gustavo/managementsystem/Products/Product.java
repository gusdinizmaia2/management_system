package com.gustavo.managementsystem.Products;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavo.managementsystem.InventoryMovements.InventoryMovement;
import com.gustavo.managementsystem.Users.User;

@Data
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

    public void setSupplier(User user){
        this.supplier = user;
    }

}
