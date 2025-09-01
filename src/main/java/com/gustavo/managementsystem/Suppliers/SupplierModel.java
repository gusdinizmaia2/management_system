package com.gustavo.managementsystem.Suppliers;

import java.util.UUID;

class SupplierModel{

    private UUID id;
    private String name;
    private String NIF;
    private String phone;
    private String address;

    public SupplierModel(String name, String NIF, String phone, String address){
        this.id = UUID.randomUUID();
        this.name = name;
        this.NIF = NIF;
        this.phone = phone;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public UUID getId(){
        return this.id;
    }

}