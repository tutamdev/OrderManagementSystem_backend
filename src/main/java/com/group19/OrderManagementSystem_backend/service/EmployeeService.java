package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.EmployeeRequest;
import com.group19.OrderManagementSystem_backend.dto.request.EmployeeUpdateRequest;
import com.group19.OrderManagementSystem_backend.dto.request.EmployeeUpdateWithPasswordRequest;
import com.group19.OrderManagementSystem_backend.dto.response.EmployeeResponse;
import com.group19.OrderManagementSystem_backend.entity.Employee;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.EmployeeMapper;
import com.group19.OrderManagementSystem_backend.repository.EmployeeRepository;
import com.group19.OrderManagementSystem_backend.utils.ERole;
import com.group19.OrderManagementSystem_backend.utils.EmployeeStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeMapper employeeMapper;


    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);
        Employee employee = employeeMapper.toEmployee(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String encodedPassword = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(encodedPassword);
        employee.setRole(ERole.EMPLOYEE.name());
        employee.setStatus(EmployeeStatus.INACTIVE.name());
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.toListEmployeeResponse(employeeRepository.findAll());
    }

    public EmployeeResponse updateEmployee(String employeeId, EmployeeUpdateRequest request) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
        employeeMapper.updateEmployee(employee, request);
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    public void deleteEmployee(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public EmployeeResponse getEmployeeById(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
        return employeeMapper.toEmployeeResponse(employee);
    }

    public EmployeeResponse getMyInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
        return employeeMapper.toEmployeeResponse(employee);
    }

    public boolean isValidEnum(String value, Class<? extends Enum> enumClass) {
        try {
            Enum.valueOf(enumClass, value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public EmployeeResponse updateEmployeeInfoWithPassword(EmployeeUpdateWithPasswordRequest request) {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        Employee employee = employeeRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if (passwordEncoder.matches(request.getOldPassword(), employee.getPassword())) {
            if (!isValidEnum(request.getRole(), ERole.class))
                throw new AppException(ErrorCode.ROLE_NOT_MATCHED);
            if (!isValidEnum(request.getStatus(), EmployeeStatus.class))
                throw new AppException(ErrorCode.STATUS_NOT_MATCHED);
            employee.setFullName(request.getFullName());
            employee.setPassword(passwordEncoder.encode(request.getNewPassword()));
            employee.setRole(request.getRole());
            employee.setStatus(request.getStatus());
            employeeRepository.save(employee);
        } else throw new AppException(ErrorCode.PASSWORD_NOT_MATCHED);
        return employeeMapper.toEmployeeResponse(employee);
    }
}
