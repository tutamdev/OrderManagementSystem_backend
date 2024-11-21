package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Area;
import com.group19.OrderManagementSystem_backend.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,String> {
    boolean existsByFoodName(String foodName);
    Optional<Food> findByFoodName(String foodName);

    List<Food> findAllByCategory_CategoryId(String categoryId);
}
