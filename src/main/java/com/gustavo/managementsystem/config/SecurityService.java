package com.gustavo.managementsystem.config;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.gustavo.managementsystem.Users.UserRepository;

@Component("securityService")
public class SecurityService {

    private UserRepository userRepository;

    // public boolean userOwner(UUID userId) {

    //     var user = userRepository.findById(userId);
    //     if (user.get().get) {
    //         return false;
    //     }

    //     var claims = (Map<String, Object>) auth.getPrincipal();
    //     Long currentUserId = (Long) claims.get("id");

    //     // Usuário pode ver os próprios dados ou se for admin
    //     return currentUserId.equals(userId) ||
    //         auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    // }
}

