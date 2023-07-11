package com.springwebservicerestfulapi.springwebservicerestfulapi.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private final UserService userService;

    public UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Object serviceResponse = userService.getUserDtoByEmail(email);
        JSONObject jsonObject = new JSONObject(serviceResponse);

        if (jsonObject.getJSONObject("data").getString("email").contains(email)) {
            return new User(email, jsonObject.getJSONObject("data").getString("password"), new ArrayList<>());
        }

        throw new UsernameNotFoundException(email);
    }

}
