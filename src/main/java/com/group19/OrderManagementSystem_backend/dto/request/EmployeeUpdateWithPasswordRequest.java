package com.group19.OrderManagementSystem_backend.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeUpdateWithPasswordRequest {
    private String username;
    private String fullName;
    private String role;
    private String status;
    private String oldPassword;
    private String newPassword;
}
