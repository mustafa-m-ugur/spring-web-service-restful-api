package com.springwebservicerestfulapi.springwebservicerestfulapi.repository;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}