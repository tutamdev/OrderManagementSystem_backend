package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.EmployeeRequest;
import com.group19.OrderManagementSystem_backend.dto.response.EmployeeResponse;
import com.group19.OrderManagementSystem_backend.entity.Employee;
import com.group19.OrderManagementSystem_backend.mapper.EmployeeMapper;
import com.group19.OrderManagementSystem_backend.repository.EmployeeRepository;
import com.group19.OrderManagementSystem_backend.utils.ERole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;


    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if(employeeRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");
        Employee employee = employeeMapper.toEmployee(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String encodedPassword =  passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        employee.setRole(ERole.EMPLOYEE.name());
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.toListEmployeeResponse(employeeRepository.findAll());
    }

    public EmployeeResponse updateEmployee(String employeeId, EmployeeRequest request) {
        Employee employee =  employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeMapper.updateEmployee(employee, request);
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
        log.info("Employee deleted successfully");
    }
}
