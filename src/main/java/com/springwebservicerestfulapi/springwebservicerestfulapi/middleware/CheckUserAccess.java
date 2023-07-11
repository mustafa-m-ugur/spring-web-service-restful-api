package com.springwebservicerestfulapi.springwebservicerestfulapi.middleware;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Role;
import com.springwebservicerestfulapi.springwebservicerestfulapi.service.TokenManagerService;
import lombok.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckUserAccess {
    @Autowired
    private TokenManagerService tokenManagerService;
    private String action_type;

    public boolean checkUserAccess(String action_type) {
        JSONObject jsonObject = new JSONObject(tokenManagerService.getUser());

        boolean roleCheck = jsonObject.getJSONObject("data").getJSONObject("role").getString("permissions").contains(action_type);

        if (roleCheck) {
            return true;
        } else {
            return false;
        }
    }
}
