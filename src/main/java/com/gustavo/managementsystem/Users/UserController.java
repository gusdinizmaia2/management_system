package com.gustavo.managementsystem.Users;

import java.lang.annotation.*;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public UserDTO postUser(@Valid @RequestBody User body){

        User user = userService.createUser(body);
    
        return modelMapper.map(user, UserDTO.class) ;
    }

    @GetMapping
    public UserDTO[] getAllUsers(){
        List<User> users =  userService.listAllUsers();

        UserDTO[] usersDTO = modelMapper.map(users,UserDTO[].class);

        return usersDTO;
    }


}
