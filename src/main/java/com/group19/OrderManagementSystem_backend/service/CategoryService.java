package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.AreaRequest;
import com.group19.OrderManagementSystem_backend.dto.request.CategoryRequest;
import com.group19.OrderManagementSystem_backend.dto.response.AreaResponse;
import com.group19.OrderManagementSystem_backend.dto.response.CategoryResponse;
import com.group19.OrderManagementSystem_backend.entity.Area;
import com.group19.OrderManagementSystem_backend.entity.Category;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.CategoryMapper;
import com.group19.OrderManagementSystem_backend.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    public CategoryResponse createCategory(CategoryRequest request) {
        if(categoryRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.CATEGORY_EXITED);
        Category category = categoryRepository.save(categoryMapper.toCategory(request));
        return categoryMapper.toCategoryResponse(category);
    }

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toListCategoryResponses(categories);
    }

    public CategoryResponse updateCategory(String name, CategoryRequest request) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXITED));
        categoryMapper.updateCategory(category, request);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public void deleteCategory(String name) {
        categoryRepository.deleteById(name);
    }
}
