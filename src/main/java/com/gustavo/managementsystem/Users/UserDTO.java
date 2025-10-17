package com.gustavo.managementsystem.Users;

import java.util.UUID;

import lombok.Data;

@Data
public class UserDTO {
    private UUID id;
    private String username;
    private String email;
    private UserRole role;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public UserRole getRole() {
        return role;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
}
