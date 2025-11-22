package com.gustavo.managementsystem.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public UserController(UserRepository userRepository,
    BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    // @Transactional
    @PostMapping
    public ResponseEntity<User> postUser(@Valid @RequestBody UserCreateDTO dto) {

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        User userInstance = modelMapper.map(dto, User.class);
        userInstance.setPassword(passwordEncoder.encode(dto.getPassword()));

        var newUser = userRepository.save(userInstance);

        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    @PreAuthorize("@authGuard.isAdmin(authentication)")
    public List<User> getAllUsers(){
        // System.out.println(token);
        List<User> users =  userService.listAllUsers();

        return users;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@authGuard.canAccessUser(authentication, #userId)")
    public Optional<User> getUserById(@PathVariable UUID userId){

        var user = userService.listUserById(userId);

        return user;
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("@authGuard.canAccessUser(authentication, #userId)")
    public Optional<User> patchUser(@PathVariable UUID userId,@Valid @RequestBody Map<String,String> body){
        
        var user = userService.patchUserById(userId, body);

        return user;
    }

    @DeleteMapping("/{userId}")
    // @PreAuthorize("hasAuthority('SCOPE_ADMIN') || authentication.principal.subject.toString() == #userId.toString()")
    @PreAuthorize("@authGuard.canAccessUser(authentication, #userId)")
    public void deleteById(@PathVariable UUID userId){

        userService.deleteUser(userId);
        // return ResponseStatus.class;
    }


}
