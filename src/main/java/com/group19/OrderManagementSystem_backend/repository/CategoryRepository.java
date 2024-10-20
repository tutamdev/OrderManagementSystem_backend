package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Area;
import com.group19.OrderManagementSystem_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String> {
    boolean existsByName(String categoryName);
    Optional<Category> findByName(String categoryName);
}
