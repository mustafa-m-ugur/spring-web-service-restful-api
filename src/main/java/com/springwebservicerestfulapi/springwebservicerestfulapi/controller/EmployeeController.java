package com.springwebservicerestfulapi.springwebservicerestfulapi.controller;

import com.springwebservicerestfulapi.springwebservicerestfulapi.request.EmployeeRequest;
import com.springwebservicerestfulapi.springwebservicerestfulapi.response.ServiceResponse;
import com.springwebservicerestfulapi.springwebservicerestfulapi.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponse> getEmployeeById(Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeDtoById(id));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> createEmployee(EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateEmployee(Long id, EmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployee(Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
