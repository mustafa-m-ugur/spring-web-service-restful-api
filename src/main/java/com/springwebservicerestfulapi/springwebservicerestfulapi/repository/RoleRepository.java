package com.springwebservicerestfulapi.springwebservicerestfulapi.repository;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}