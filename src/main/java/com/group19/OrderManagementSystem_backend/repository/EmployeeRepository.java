package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    boolean existsByUsername(String username);
}
