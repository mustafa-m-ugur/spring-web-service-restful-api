package com.springwebservicerestfulapi.springwebservicerestfulapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private Long roleId;
    private Long accountId;
}
