package com.springwebservicerestfulapi.springwebservicerestfulapi.controller;

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
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            String token = tokenManagerService.generateToken(loginRequest.getEmail());

            tokenManagerService.setLoginToken(token);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("access_token", token);

            return ResponseEntity.ok(jsonObject.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
