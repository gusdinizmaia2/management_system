package com.gustavo.managementsystem.Users;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data 
public class UserCreateDTO{
    @NotBlank
    @NotNull
    @Size(min = 3, max = 30)
    private String username;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min = 4, max = 16)
    private String password;

    @NotNull
    private UserRole role;

    public String username() {
        return username;
    }
    
    public String password(){
        return password;
    }

    public String email(){
        return email;
    }
    
    public UserRole role(){
        return role;
    }
}