package com.gustavo.managementsystem.Users;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.gustavo.managementsystem.InventoryMovements.InventoryMovement;

@Data
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 3)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 8)
    private String password;

    @Column()
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "owner_user")
    private List<InventoryMovement> inventory_movements;

}
