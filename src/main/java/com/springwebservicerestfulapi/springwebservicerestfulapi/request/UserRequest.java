package com.springwebservicerestfulapi.springwebservicerestfulapi.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}