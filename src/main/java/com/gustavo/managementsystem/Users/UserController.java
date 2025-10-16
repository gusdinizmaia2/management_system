package com.gustavo.managementsystem.Users;

import java.lang.annotation.*;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<Void> postUser(@RequestBody UserCreateDTO dto) {

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRole(dto.role());

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public UserDTO[] getAllUsers(){
        List<User> users =  userService.listAllUsers();

        UserDTO[] usersDTO = modelMapper.map(users,UserDTO[].class);

        return usersDTO;
    }

    @GetMapping
    public UserDTO getUserById(@PathVariable Long userId){

        var user = userService.listUserById(userId);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @PatchMapping
    public UserDTO patchUser(@PathVariable Long userId,@Valid @RequestBody Map<String,String> body){
        
        var user = userService.patchUserById(userId, body);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long userId){

        userService.deleteUser(userId);
    }


}
