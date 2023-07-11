package com.springwebservicerestfulapi.springwebservicerestfulapi.controller;

import com.springwebservicerestfulapi.springwebservicerestfulapi.request.UserRequest;
import com.springwebservicerestfulapi.springwebservicerestfulapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        if (this.checkUserAccess("users_index"))
            return ResponseEntity.ok(userService.getAllUser());
        else
            return ResponseEntity.badRequest().body("You don't have permission to access this page");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(Long id) {
        return ResponseEntity.ok(userService.getUserDtoById(id));
    }

    @PostMapping
    public ResponseEntity<Object> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(Long id, UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
