package com.gustavo.managementsystem.Suppliers;


import java.util.List;

import com.gustavo.managementsystem.Products.Product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity(name="suppliers")
public class SupplierCreateDTO{

    @NotBlank
    @NotNull
    @Size(min = 3, max = 150)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 9, max = 9)
    private String NIF;

    @NotBlank
    @NotNull
    @Size(min = 9, max = 9)
    private String phone;

    @Size(max = 200)
    private String address;

}