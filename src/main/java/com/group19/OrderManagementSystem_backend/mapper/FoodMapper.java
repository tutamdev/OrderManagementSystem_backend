package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.FoodRequest;
import com.group19.OrderManagementSystem_backend.dto.response.FoodResponse;
import com.group19.OrderManagementSystem_backend.entity.Category;
import com.group19.OrderManagementSystem_backend.entity.Food;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.repository.CategoryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class FoodMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Mapping(source = "category", target = "category", qualifiedByName = "mapStringToCategory")
    public abstract Food toFood(FoodRequest foodRequest);

    @Mapping(source = "category", target = "category")
    public abstract FoodResponse toFoodResponse(Food food);

    public abstract List<FoodResponse> toListFoodResponse(List<Food> foods);

    @Mapping(source = "category", target = "category", qualifiedByName = "mapStringToCategory")
    public abstract void updateFood(@MappingTarget Food food, FoodRequest foodRequest);

    @Named("mapStringToCategory")
    public Category mapStringToCategory(String categoryName) {
        return categoryRepository.findById(categoryName)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXITED));
    }
}
