package com.gustavo.session;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavo.managementsystem.Users.User;
import com.gustavo.managementsystem.Users.UserRepository;
import com.gustavo.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class SessionController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired 
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User payload){ 

        var foundUser = userRepository.findByUsername(payload.getUsername());

        if(foundUser.isPresent()){
            var message = new HashMap<String, String>();
            message.put("error","Username already exists");
            return ResponseEntity.status(409).body(message);
        }

        var userBuilder = User.builder()
            .username(payload.getUsername())
            .password(payload.getPassword())
            .build();

            var user = userRepository.save(userBuilder);
            return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User payload){

        var authToken = new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword());
        var authentication = authenticationManager.authenticate(authToken);
        var token = jwtTokenProvider.createToken(authentication);

        var message = new HashMap<String, String>();
        message.put("token", token);

        return ResponseEntity.status(200).body(message);
    }
}
