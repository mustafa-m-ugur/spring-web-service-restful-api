package com.springwebservicerestfulapi.springwebservicerestfulapi.config;

import com.springwebservicerestfulapi.springwebservicerestfulapi.service.TokenManagerService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenManagerService tokenManagerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String email = null;
        String token = null;

        if (authHeader != null && authHeader.contains("Bearer")) {
            token = authHeader.substring(7);
            email = tokenManagerService.getEmailToken(token);
        }

        if (email != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            request.setAttribute("email", email);
            if (!tokenManagerService.tokenValidate(token)) {
                throw new RuntimeException("Invalid Token");
            }

            UsernamePasswordAuthenticationToken upassToken = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
            upassToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(upassToken);
        }

        filterChain.doFilter(request, response);
    }
}
