package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.EmployeeRequest;
import com.group19.OrderManagementSystem_backend.dto.request.EmployeeUpdateRequest;
import com.group19.OrderManagementSystem_backend.dto.request.EmployeeUpdateWithPasswordRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.EmployeeResponse;
import com.group19.OrderManagementSystem_backend.entity.Employee;
import com.group19.OrderManagementSystem_backend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public ApiResponse<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest request) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.createEmployee(request))
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<EmployeeResponse>> getAllEmployees() {
        return ApiResponse.<List<EmployeeResponse>>builder()
                .result(employeeService.getAllEmployees())
                .build();
    }

    @PutMapping("/{employeeId}")
    public ApiResponse<EmployeeResponse> updateEmployee(@PathVariable("employeeId") String employeeId, @RequestBody EmployeeUpdateRequest request) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.updateEmployee(employeeId, request))
                .build();
    }

    @GetMapping("/{EmployeeId}")
    public ApiResponse<EmployeeResponse> getEmployeeById(@PathVariable String EmployeeId) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.getEmployeeById(EmployeeId))
                .build();
    }

    @DeleteMapping("/{employeeId}")
    public ApiResponse<Void> deleteEmployee(@PathVariable("employeeId") String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ApiResponse.<Void>builder()
                .message("OK")
                .build();
    }

    @GetMapping("/info")
    public ApiResponse<EmployeeResponse> getEmployeeInfo() {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.getMyInfo())
                .build();
    }
    @PutMapping("/info")
    public ApiResponse<EmployeeResponse> updateEmployeeInfoWithPassword(@RequestBody EmployeeUpdateWithPasswordRequest request) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.updateEmployeeInfoWithPassword(request))
                .build();
    }
}
