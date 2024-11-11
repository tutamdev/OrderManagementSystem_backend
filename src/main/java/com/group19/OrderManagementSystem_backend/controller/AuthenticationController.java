package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.AuthenticationRequest;
import com.group19.OrderManagementSystem_backend.dto.request.EmployeeRequest;
import com.group19.OrderManagementSystem_backend.dto.request.IntrospectRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.AuthenticationResponse;
import com.group19.OrderManagementSystem_backend.dto.response.EmployeeResponse;
import com.group19.OrderManagementSystem_backend.dto.response.IntrospectResponse;
import com.group19.OrderManagementSystem_backend.service.AuthenticationService;
import com.group19.OrderManagementSystem_backend.service.EmployeeService;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/register")
    public ApiResponse<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest request) {
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.createEmployee(request))
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse token = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(token)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        IntrospectResponse isAuthenticated = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(isAuthenticated)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .message("Successfully logged out")
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refresh(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        AuthenticationResponse token = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(token)
                .build();
    }
}
