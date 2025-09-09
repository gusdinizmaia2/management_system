package com.gustavo.managementsystem.InventoryMovements;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Data
public class InventoryMovementCreateDTO{

    @NotBlank
    @NotEmpty
    private InventoryMovementTypes type;

    @NotBlank
    @NotNull
    @Positive
    private Integer quantity;

    @NotEmpty
    private String date;

    @NotEmpty
    @NotBlank
    private Integer product_id;
}