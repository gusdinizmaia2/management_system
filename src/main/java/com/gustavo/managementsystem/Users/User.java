package com.gustavo.managementsystem.Users;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gustavo.managementsystem.InventoryMovements.InventoryMovement;
import com.gustavo.managementsystem.Products.Product;

@Data
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id", unique = true)
    private UUID id;

    @Column(nullable = false, length=30)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @JsonIgnore
    @OneToMany(mappedBy = "owner_user")
    private List<InventoryMovement> inventory_movements;

    @JsonIgnore
    @OneToMany(mappedBy = "supplier")
    private List<Product> products_owner;

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }

     public UUID getUserId() {
        return id;
    }
    
    public UserRole getRole() {
    return role;
}

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}
