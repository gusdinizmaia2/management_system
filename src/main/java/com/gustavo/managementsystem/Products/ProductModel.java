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

    public void update(Optional<String> name,Optional<String> description,Optional<Double> price, Optional<Integer> quantity) {

        name.ifPresent(n -> this.name = n);
        description.ifPresent(n -> this.description = n);
        price.ifPresent(n -> this.price = n);
        quantity.ifPresent(n -> this.quantity = n);

    }

}
