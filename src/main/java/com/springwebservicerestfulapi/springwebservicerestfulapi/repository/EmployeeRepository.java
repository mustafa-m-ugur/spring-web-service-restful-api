package com.springwebservicerestfulapi.springwebservicerestfulapi.repository;

import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
