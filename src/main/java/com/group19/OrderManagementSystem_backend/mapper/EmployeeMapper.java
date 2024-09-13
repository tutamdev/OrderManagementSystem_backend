package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.EmployeeRequest;
import com.group19.OrderManagementSystem_backend.dto.response.EmployeeResponse;
import com.group19.OrderManagementSystem_backend.entity.Employee;
import com.group19.OrderManagementSystem_backend.repository.EmployeeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeRequest request);
    EmployeeResponse toEmployeeResponse(Employee employee);
    void updateEmployee(@MappingTarget Employee employee, EmployeeRequest request);
    List<EmployeeResponse> toListEmployeeResponse(List<Employee> employees);
}
