package com.gustavo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gustavo.managementsystem.Users.UserRepository;

public class SessionConfig {
    
    @Bean
    UserDetailsService userDetailsService(UserRepository userRepo){
        return (username) -> userRepo
            .findByUsername(username)
            .orElseThrow(() -> {
                var error = String.format("Username: %s not found.", username);
                return new UsernameNotFoundException(error);
            });
    }

    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService){

        return authentication -> {
            var username = authentication.getPrincipal().toString();
            var password = authentication.getCredentials().toString();

            try{
                var user = userDetailsService.loadUserByUsername(username);

                if(!user.getPassword().equals(password)){
                    throw new BadCredentialsException("Invalid username/password user");
                }

                if(!user.isEnabled()){
                    throw new DisabledException("User account is not active");
                }

                return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());

            } catch(UsernameNotFoundException e){
                throw new BadCredentialsException("Invalid username/password");
            }
        };
    }
}
