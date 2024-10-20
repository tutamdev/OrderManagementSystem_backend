package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.CategoryRequest;
import com.group19.OrderManagementSystem_backend.dto.request.FoodRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.CategoryResponse;
import com.group19.OrderManagementSystem_backend.dto.response.FoodResponse;
import com.group19.OrderManagementSystem_backend.service.CategoryService;
import com.group19.OrderManagementSystem_backend.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    FoodService foodService;

    @PostMapping("")
    public ApiResponse<FoodResponse> createFood(@RequestBody FoodRequest foodRequest) {
        return ApiResponse.<FoodResponse>builder()
                .result(foodService.createFood(foodRequest))
                .message("Create food successfully")
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<FoodResponse>> getAllFoods() {
        return ApiResponse.<List<FoodResponse>>builder()
                .result(foodService.getAll())
                .message("Successfully retrieved all foods")
                .build();
    }

    @PutMapping("/{foodId}")
    public ApiResponse<FoodResponse> updateFood(@PathVariable("foodId") String foodId, @RequestBody FoodRequest request) {
        return ApiResponse.<FoodResponse>builder()
                .result(foodService.updateFood(foodId, request))
                .message("Successfully updated food")
                .build();
    }

    @DeleteMapping("/{foodId}")
    public ApiResponse<Void> deleteFood(@PathVariable String foodId) {
        foodService.deleteFood(foodId);
        return ApiResponse.<Void>builder()
                .message("Delete food successfully")
                .build();
    }
}
