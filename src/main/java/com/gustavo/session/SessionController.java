package com.gustavo.session;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.managementsystem.Users.User;

@RestController
@RequestMapping("/auth")
public class SessionController {
    
    @PostMapping("/register")
    public ResponseEntity<?> register() {
        return ResponseEntity.status(201).body("register route!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() {
        return ResponseEntity.status(200).body("login route!");
    }
}
