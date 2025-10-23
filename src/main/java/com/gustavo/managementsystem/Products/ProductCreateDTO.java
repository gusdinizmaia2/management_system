package com.gustavo.managementsystem.Products;

import com.gustavo.managementsystem.Users.User;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductCreateDTO {
    
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @Size(max = 255)
    private String description;

    @Positive
    @DecimalMin(value = "0.00")
    @NotNull
    @NotBlank
    private double price;

    @Positive
    @NotBlank
    @NotNull
    private int quantity;

    private User supplier;

    // @Positive
    // @NotBlank
    // @NotNull
    // private int supplier_id;
}
