package com.springwebservicerestfulapi.springwebservicerestfulapi.dto;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeDtoConverter {
    public EmployeeDto convert(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setGsm(employee.getGsm());
        employeeDto.setTitle(employee.getTitle());
        employeeDto.setAccountId(employee.getAccountId());
        employeeDto.setRoleId(employee.getRoleId());
        employeeDto.setRole(employee.getRole());
        return employeeDto;
    }
}
