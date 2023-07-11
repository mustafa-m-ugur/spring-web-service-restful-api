package com.springwebservicerestfulapi.springwebservicerestfulapi.controller;

import com.springwebservicerestfulapi.springwebservicerestfulapi.middleware.CheckUserAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController {
    @Autowired
    private CheckUserAccess checkUserAccess;

    public boolean checkUserAccess(String action_type) {
        return checkUserAccess.checkUserAccess(action_type);
    }

}
