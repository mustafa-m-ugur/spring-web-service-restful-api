package com.springwebservicerestfulapi.springwebservicerestfulapi.dto;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}