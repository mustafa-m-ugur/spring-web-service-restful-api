package com.springwebservicerestfulapi.springwebservicerestfulapi.controller;

import com.springwebservicerestfulapi.springwebservicerestfulapi.dto.TokenDto;
import com.springwebservicerestfulapi.springwebservicerestfulapi.request.LoginRequest;
import com.springwebservicerestfulapi.springwebservicerestfulapi.service.TokenManagerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private TokenManagerService tokenManagerService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginController(TokenManagerService tokenManagerService, AuthenticationManager authenticationManager) {
        this.tokenManagerService = tokenManagerService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            TokenDto tokenDto = tokenManagerService.generateToken(loginRequest.getEmail());
            tokenManagerService.setLoginToken(tokenDto.getAccess_token());
            return ResponseEntity.ok(tokenDto);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
