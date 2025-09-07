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
    @Size(min = 8)
    private String password;

    private Boolean is_admin;
}