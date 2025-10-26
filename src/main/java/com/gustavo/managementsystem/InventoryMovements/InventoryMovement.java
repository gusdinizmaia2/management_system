package com.gustavo.managementsystem.InventoryMovements;

import lombok.Data;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavo.managementsystem.Products.Product;
import com.gustavo.managementsystem.Users.User;


@Data
@Entity(name= "inventory_movements")
public class InventoryMovement{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private InventoryMovementTypes type;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime date_time;

    @PrePersist
    protected void onCreateDateTime() {
        var newDateTime = LocalDateTime.now();

        //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y h:m");

        if(this.date_time == null){
            this.date_time = newDateTime;
        }
    }

    @ManyToOne
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id", unique=false)
    @JsonIgnore
    private User owner_user;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    }