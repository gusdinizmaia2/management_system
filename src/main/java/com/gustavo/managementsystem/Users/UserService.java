package com.gustavo.managementsystem.Users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    // public Optional<User> listUserById(Long id){
    //     return userRepository.findById(id);
    // }
}
