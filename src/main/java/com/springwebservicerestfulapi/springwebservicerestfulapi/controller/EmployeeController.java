package com.springwebservicerestfulapi.springwebservicerestfulapi.controller;

import com.springwebservicerestfulapi.springwebservicerestfulapi.request.EmployeeRequest;
import com.springwebservicerestfulapi.springwebservicerestfulapi.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController extends BaseController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllEmployee() {
        if (this.checkUserAccess("employees_index"))
            return ResponseEntity.ok(employeeService.getAllEmployee());
        else
            return ResponseEntity.badRequest().body("You don't have permission to access this page");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(Long id) {
        if (this.checkUserAccess("employees_index"))
            return ResponseEntity.ok(employeeService.getEmployeeDtoById(id));
        else
            return ResponseEntity.badRequest().body("You don't have permission to access this page");
    }

    @PostMapping
    public ResponseEntity<Object> createEmployee(EmployeeRequest employeeRequest) {
        if (this.checkUserAccess("employees_create"))
            return ResponseEntity.ok(employeeService.createEmployee(employeeRequest));
        else
            return ResponseEntity.badRequest().body("You don't have permission to access this page");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEmployee(Long id, EmployeeRequest employeeRequest) {
        if (this.checkUserAccess("employees_edit"))
            return ResponseEntity.ok(employeeService.updateEmployee(id, employeeRequest));
        else
            return ResponseEntity.badRequest().body("You don't have permission to access this page");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(Long id) {
        if (this.checkUserAccess("employees_destroy")) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.badRequest().build();
    }


}
