package com.springwebservicerestfulapi.springwebservicerestfulapi.dto;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gsm;
    private String title;
    private Long accountId;
    private Long roleId;
    private Role role;
}
