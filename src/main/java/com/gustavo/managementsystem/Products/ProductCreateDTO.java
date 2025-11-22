package com.gustavo.managementsystem.Products;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
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
    private double price;

    @Positive
    @NotNull
    private int quantity;

    // @Positive
    // @NotBlank
    // @NotNull
    // private int supplier_id;
}
