package com.group19.OrderManagementSystem_backend.configuration;

import com.group19.OrderManagementSystem_backend.entity.Employee;
import com.group19.OrderManagementSystem_backend.repository.EmployeeRepository;
import com.group19.OrderManagementSystem_backend.utils.ERole;
import com.group19.OrderManagementSystem_backend.utils.EmployeeStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(EmployeeRepository employeeRepository) {
        return args -> {
            if (employeeRepository.findByUsername("admin").isEmpty()) {
                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
                Employee admin = Employee.builder()
                        .fullName("Administrator")
                        .username("admin")
                        .role(ERole.ADMIN.name())
                        .status(EmployeeStatus.ACTIVE.name())
                        .password(passwordEncoder.encode("admin"))
                        .build();
                employeeRepository.save(admin);
                log.warn("Admin has been created");
            }
        };
    }

}
