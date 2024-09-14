package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String role;
}
