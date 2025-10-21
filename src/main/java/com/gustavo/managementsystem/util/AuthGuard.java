package com.gustavo.managementsystem.util;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.gustavo.managementsystem.Products.Product;
import com.gustavo.managementsystem.Products.ProductRepository;
import com.gustavo.managementsystem.Users.User;
import com.gustavo.managementsystem.Users.UserRepository;

@Component("authGuard")
public class AuthGuard {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    public boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_ADMIN"));
    }

    public boolean canAccessUser(Authentication authentication, UUID userId) {
        String subject = authentication.getName();
        boolean isOwner = subject.equals(userId.toString());

        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_ADMIN"));

        return isAdmin || isOwner;
    }

    public boolean isSupplier(Authentication authentication) {
        return authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_SUPPLIER"));
    }

    public boolean isSupplierOwner(Authentication authentication, Long productId) {
        String userId = authentication.getName(); 
        Optional<Product> product = productRepository.findById(productId);

        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        var userOwner = product.get().getSupplier().getUserId().toString();

        boolean isOwner = userOwner.equals(userId);

        boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("SCOPE_ADMIN"));

        return isAdmin || isOwner;
    }
}
