package com.gustavo.managementsystem.util;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("authGuard")
public class AuthGuard {

    public boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_ADMIN"));
    }

    public boolean canAccessUser(Authentication authentication, UUID userId) {
        String subject = authentication.getName(); // o subject do JWT
        boolean isOwner = subject.equals(userId.toString());

        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_ADMIN"));

        return isAdmin || isOwner;
    }
}
