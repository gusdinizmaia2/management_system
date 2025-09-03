package com.gustavo.managementsystem.Users;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column()
    private Boolean is_admin;

}
