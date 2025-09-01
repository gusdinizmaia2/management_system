package com.gustavo.managementsystem.Products;
import java.util.Optional;
import java.util.UUID;

public class ProductModel {
    
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public ProductModel(String name, String description, double price, int quantity){

        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;

    }


    public UUID getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }

    public String setName(String name){
        
        return this.name = name;
    }

}
