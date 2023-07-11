package com.springwebservicerestfulapi.springwebservicerestfulapi.response;

import org.springframework.stereotype.Component;

@Component
public class ServiceResponse {

    public String message;
    public Object data;
    public Boolean status;

    public ServiceResponse() {
    }

    public ServiceResponse(String message, Object data, Boolean status) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public ServiceResponse(String message) {
        this.message = message;
    }

    public ServiceResponse(Object data) {
        this.data = data;
    }

    public static ServiceResponse success(String message, Object data, Boolean status) {
        return new ServiceResponse(message, data, status);
    }

    public static ServiceResponse success(Object data) {
        return new ServiceResponse(data);
    }

    public static ServiceResponse error(String message) {
        return new ServiceResponse(message);
    }

    public static ServiceResponse error(String message, Object data) {
        return new ServiceResponse(message, data, false);
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Boolean getStatus() {
        return status;
    }

}

