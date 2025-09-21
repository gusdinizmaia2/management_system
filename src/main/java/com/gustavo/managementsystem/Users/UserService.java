package com.gustavo.managementsystem.Users;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> listUserById(Long id,Map<String,String> body){
         User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        
        body.forEach((key, value) -> {
            switch (key) {
                case "username" -> user.setUsername(value);
            }
        });

        var update = userRepository.save(user);

        return Optional.of(update);
    }
}
