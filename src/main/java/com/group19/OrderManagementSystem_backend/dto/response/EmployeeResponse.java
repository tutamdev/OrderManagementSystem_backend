package com.group19.OrderManagementSystem_backend.dto.response;

import com.group19.OrderManagementSystem_backend.utils.ERole;
import lombok.*;

import javax.management.relation.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private String id;
    private String username;
    private String fullName;
    private String role;
    private String status;
}
