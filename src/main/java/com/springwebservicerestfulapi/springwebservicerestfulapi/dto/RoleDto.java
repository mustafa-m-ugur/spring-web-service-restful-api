package com.springwebservicerestfulapi.springwebservicerestfulapi.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private String permissions;
}
