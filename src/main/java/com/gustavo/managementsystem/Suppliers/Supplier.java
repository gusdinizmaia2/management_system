package com.gustavo.managementsystem.Suppliers;


import java.util.List;

import com.gustavo.managementsystem.Products.Product;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="suppliers")
public class Supplier{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(nullable = false)
    private String NIF;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    

}