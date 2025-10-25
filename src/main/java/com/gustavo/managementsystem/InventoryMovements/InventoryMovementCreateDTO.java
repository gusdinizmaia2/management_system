package com.gustavo.managementsystem.InventoryMovements;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gustavo.managementsystem.Products.Product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Data
public class InventoryMovementCreateDTO{

    @NotNull(message = "the attribute 'role' cant to be null")
    private InventoryMovementTypes type;

    @NotNull
    @Positive
    private Integer quantity;

    // @JsonFormat(pattern = "d/M/y H:m")
    // private LocalDateTime date;

    // @NotEmpty
    // @NotBlank
    // private Product product;
}