package com.gustavo.managementsystem.Users;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.gustavo.managementsystem.InventoryMovements.InventoryMovement;

@Data
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    @Column(name="id")
    private UUID userId;

    @Column(nullable = false, length = 3)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 8)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "owner_user")
    private List<InventoryMovement> inventory_movements;

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }

     public UUID getUserId() {
        return userId;
    }
    
    public UserRole getRole() {
    return role;
}

    public void setRole(UserRole role) {
        this.role = role;
    }

    // public void setRole(UserRole newRole){
    //     role = newRole;
    // }

}
