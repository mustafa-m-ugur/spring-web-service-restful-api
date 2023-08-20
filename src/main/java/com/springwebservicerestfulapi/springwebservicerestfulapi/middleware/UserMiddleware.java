package com.springwebservicerestfulapi.springwebservicerestfulapi.middleware;

import com.springwebservicerestfulapi.springwebservicerestfulapi.dto.UserDto;
import com.springwebservicerestfulapi.springwebservicerestfulapi.service.TokenManagerService;
import com.springwebservicerestfulapi.springwebservicerestfulapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class UserMiddleware {
    private final UserService userService;
    private TokenManagerService tokenManagerService;

    public UserMiddleware(UserService userService, TokenManagerService tokenManagerService) {
        this.userService = userService;
        this.tokenManagerService = tokenManagerService;
    }

    public UserDto getUserInfo() {
        JSONObject jsonObject = new JSONObject(tokenManagerService.getUser());
        UserDto userDto = new UserDto();
        userDto.setFirstName(jsonObject.getJSONObject("data").getString("firstName"));
        userDto.setLastName(jsonObject.getJSONObject("data").getString("lastName"));
        userDto.setEmail(jsonObject.getJSONObject("data").getString("email"));
        userDto.setId(jsonObject.getJSONObject("data").getLong("id"));

        return userDto;
    }
}
