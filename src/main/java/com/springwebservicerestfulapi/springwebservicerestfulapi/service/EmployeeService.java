package com.springwebservicerestfulapi.springwebservicerestfulapi.service;

import com.springwebservicerestfulapi.springwebservicerestfulapi.dto.EmployeeDto;
import com.springwebservicerestfulapi.springwebservicerestfulapi.dto.EmployeeDtoConverter;
import com.springwebservicerestfulapi.springwebservicerestfulapi.model.Employee;
import com.springwebservicerestfulapi.springwebservicerestfulapi.repository.EmployeeRepository;
import com.springwebservicerestfulapi.springwebservicerestfulapi.request.EmployeeRequest;
import com.springwebservicerestfulapi.springwebservicerestfulapi.request.UserRequest;
import com.springwebservicerestfulapi.springwebservicerestfulapi.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    private final ServiceResponse serviceResponse;
    private final EmployeeDtoConverter employeeDtoConverter;
    private final UserService userService;

    public EmployeeService(EmployeeRepository employeeRepository, ServiceResponse serviceResponse, EmployeeDtoConverter employeeDtoConverter, UserService userService) {
        this.employeeRepository = employeeRepository;
        this.serviceResponse = serviceResponse;
        this.employeeDtoConverter = employeeDtoConverter;
        this.userService = userService;
    }

    private Pageable createPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pageable;
    }

    public ServiceResponse getAllEmployee() {
        ServiceResponse response = null;

        try {
            Pageable pageable = createPageable(1, 1);
            Page<Employee> employeeList = employeeRepository.findAll(pageable);
            List<EmployeeDto> employeeDtoList = new ArrayList<>();

            for (Employee employee : employeeList) {
                employeeDtoList.add(employeeDtoConverter.convert(employee));
            }

            response = serviceResponse.success("Success", employeeDtoList, true);

        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public ServiceResponse getEmployeeDtoById(Long id) {
        ServiceResponse response = null;

        try {
            Optional<Employee> employeeOptional = employeeRepository.findById(id);
            EmployeeDto data = employeeOptional.map(employeeDtoConverter::convert).orElse(new EmployeeDto());

            response = serviceResponse.success("Success", data, true);

        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public ServiceResponse createEmployee(EmployeeRequest employeeRequest) {
        ServiceResponse response = null;
        try {

            Employee employee = new Employee();
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setEmail(employeeRequest.getEmail());
            employee.setTitle(employeeRequest.getTitle());
            employee.setRoleId(employeeRequest.getRoleId());
            employee.setAccountId(employeeRequest.getAccountId());

            employeeRepository.save(employee);

            UserRequest userRequest = new UserRequest();
            userRequest.setFirstName(employee.getFirstName());
            userRequest.setLastName(employee.getLastName());
            userRequest.setEmail(employee.getEmail());
            userRequest.setPassword("dasdasd");

            userService.createUser(userRequest);

            response = serviceResponse.success("Success", employeeDtoConverter.convert(employee), true);

        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public ServiceResponse updateEmployee(Long id, EmployeeRequest employeeRequest) {
        ServiceResponse response = null;
        try {

            Optional<Employee> employeeOptional = employeeRepository.findById(id);

            employeeOptional.ifPresent(employee -> {
                employee.setFirstName(employeeRequest.getFirstName());
                employee.setLastName(employeeRequest.getLastName());
                employee.setEmail(employeeRequest.getEmail());
                employee.setTitle(employeeRequest.getTitle());
                employee.setRoleId(employeeRequest.getRoleId());
                employee.setAccountId(employeeRequest.getAccountId());

                employeeRepository.save(employee);
            });

            EmployeeDto data = employeeOptional.map(employeeDtoConverter::convert).orElse(new EmployeeDto());

            response = serviceResponse.success("Success", data, true);

        } catch (Exception e) {
            response = serviceResponse.error(e.getMessage(), "");
        }

        return response;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
