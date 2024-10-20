package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.AreaRequest;
import com.group19.OrderManagementSystem_backend.dto.request.CategoryRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.AreaResponse;
import com.group19.OrderManagementSystem_backend.dto.response.CategoryResponse;
import com.group19.OrderManagementSystem_backend.service.AreaService;
import com.group19.OrderManagementSystem_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("")
    public ApiResponse<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.createCategory(categoryRequest))
                .message("Create category successfully")
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<CategoryResponse>> getAllCategories() {
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAllCategories())
                .message("Successfully retrieved all categories")
                .build();
    }

    @PutMapping("/{categoryId}")
    public ApiResponse<CategoryResponse> updateCategory(@PathVariable("categoryId") String categoryId, @RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.updateCategory(categoryId, categoryRequest))
                .message("Successfully updated category")
                .build();
    }

    @DeleteMapping("/{categoryId}")
    public ApiResponse<Void> deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
        return ApiResponse.<Void>builder()
                .message("Delete category successfully")
                .build();
    }
}
