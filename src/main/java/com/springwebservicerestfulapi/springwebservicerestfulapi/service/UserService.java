package com.springwebservicerestfulapi.springwebservicerestfulapi.service;

import com.springwebservicerestfulapi.springwebservicerestfulapi.dto.UserDto;
import com.springwebservicerestfulapi.springwebservicerestfulapi.dto.UserDtoConverter;
import com.springwebservicerestfulapi.springwebservicerestfulapi.model.User;
import com.springwebservicerestfulapi.springwebservicerestfulapi.repository.UserRepository;
import com.springwebservicerestfulapi.springwebservicerestfulapi.request.UserRequest;
import com.springwebservicerestfulapi.springwebservicerestfulapi.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final ServiceResponse serviceResponse;

    public UserService(UserRepository userRepository, UserDtoConverter userDtoConverter, ServiceResponse serviceResponse) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
        this.serviceResponse = serviceResponse;
    }

    public ServiceResponse getAllUser() {
        ServiceResponse response = null;
        try {
            List<User> userList = userRepository.findAll();
            List<UserDto> userDtoList = new ArrayList<>();

            for (User user : userList) {
                userDtoList.add(userDtoConverter.convert(user));
            }

            response = serviceResponse.success("Success", userDtoList, true);
        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public ServiceResponse getUserDtoById(Long id) {
        ServiceResponse response = null;
        try {
            Optional<User> userOptional = userRepository.findById(id);
            UserDto data = userOptional.map(userDtoConverter::convert).orElse(new UserDto());

            response = serviceResponse.success("Success", data, true);
        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public ServiceResponse getUserDtoByEmail(String email) {
        ServiceResponse response = null;
        try {
            User userOptional = userRepository.findByEmail(email);
            if (userOptional == null) {
                return null;
            }

            UserDto data = userDtoConverter.convert(userOptional);

            response = serviceResponse.success("Success", data, true);
        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage());
        }

        return response;
    }

    public ServiceResponse createUser(UserRequest userRequest) {
        ServiceResponse response = null;
        try {
            User user = new User();
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(encoder().encode(userRequest.getPassword()));

            userRepository.save(user);

            UserDto data = userDtoConverter.convert(user);

            response = serviceResponse.success("Success", data, true);
        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public ServiceResponse updateUser(Long id, UserRequest userRequest) {
        ServiceResponse response = null;
        try {
            Optional<User> userOptional = userRepository.findById(id);

            userOptional.ifPresent(user -> {
                user.setFirstName(userRequest.getFirstName());
                user.setLastName(userRequest.getLastName());
                user.setEmail(userRequest.getEmail());
                user.setPassword(userRequest.getPassword());
                userRepository.save(user);
            });

            UserDto data = userOptional.map(userDtoConverter::convert).orElse(new UserDto());

            response = serviceResponse.success("Success", data, true);
        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}