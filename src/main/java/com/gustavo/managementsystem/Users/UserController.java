package com.gustavo.managementsystem.Users;

import java.lang.annotation.*;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<User> getAllUsers(){
        List<User> users =  userService.listAllUsers();

        // UserDTO[] usersDTO = modelMapper.map(users,UserDTO[].class);

        return users;
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId){

        var user = userService.listUserById(userId);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @PatchMapping("/{userId}")
    public UserDTO patchUser(@PathVariable Long userId,@Valid @RequestBody Map<String,String> body){
        
        var user = userService.patchUserById(userId, body);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable Long userId){

        userService.deleteUser(userId);
    }


}
