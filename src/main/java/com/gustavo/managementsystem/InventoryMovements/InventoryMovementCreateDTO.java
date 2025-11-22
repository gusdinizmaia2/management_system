package com.gustavo.managementsystem.InventoryMovements;

import lombok.Getter;
import jakarta.validation.constraints.*;

@Getter
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