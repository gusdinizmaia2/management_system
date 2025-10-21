package com.gustavo.managementsystem.Users;

import java.lang.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @PostMapping
    public ResponseEntity<User> postUser(@Valid @RequestBody UserCreateDTO dto) {

        var userFromDb = userRepository.findByUsername(dto.username());
        if (userFromDb.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setUsername(dto.username());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setEmail(dto.email());
        user.setRole(dto.role());

        var newUser = userRepository.save(user);

        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    @PreAuthorize("@authGuard.isAdmin(authentication)")
    public UserDTO[] getAllUsers(){
        // System.out.println(token);
        List<User> users =  userService.listAllUsers();

        UserDTO[] usersDTO = modelMapper.map(users,UserDTO[].class);

        return usersDTO;
    }

    @GetMapping("/{userId}")
    @PreAuthorize("@authGuard.canAccessUser(authentication, #userId)")
    // @PreAuthorize("authentication.principal.claims[''] == '35c836ae-3d5b-427e-a738-32ac7f414cf4'")
    // @PreAuthorize("")
    public UserDTO getUserById(@PathVariable UUID userId, @AuthenticationPrincipal Jwt jwt){

        var user = userService.listUserById(userId);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("@authGuard.canAccessUser(authentication, #userId)")
    public UserDTO patchUser(@PathVariable UUID userId,@Valid @RequestBody Map<String,String> body){
        
        var user = userService.patchUserById(userId, body);

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return userDTO;
    }

    @DeleteMapping("/{userId}")
    // @PreAuthorize("hasAuthority('SCOPE_ADMIN') || authentication.principal.subject.toString() == #userId.toString()")
    @PreAuthorize("@authGuard.canAccessUser(authentication, #userId)")
    public void deleteById(@RequestParam UUID userId){

        userService.deleteUser(userId);
    }


}
