package com.gustavo.security;

import java.io.IOException;
import org.springframework.http.HttpHeaders;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtTokenAuthentication extends GenericFilterBean {
    
    public static final String HEADER_PREFIX = "Bearer ";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException{

        var token = resolveToken((HttpServletRequest) req);

        if(token != null && jwtTokenProvider.validateToken(token)){
            var auth = jwtTokenProvider.getAuthentication(token);

            if(auth != null && !(auth instanceof AnonymousAuthenticationToken)) {

                var context = SecurityContextHolder.createEmptyContext();

                context.setAuthentication(auth);
                SecurityContextHolder.setContext(context);
            }
        }

        filterChain.doFilter(req, res);
    }

    private String resolveToken(HttpServletRequest request){
        var bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)){
            return bearerToken.substring(7);
        }

        return null;
    }
}


