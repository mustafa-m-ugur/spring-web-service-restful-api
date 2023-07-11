package com.springwebservicerestfulapi.springwebservicerestfulapi.dto;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoConverter {
    public RoleDto convert(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setPermissions(role.getPermissions());
        return roleDto;
    }
}
