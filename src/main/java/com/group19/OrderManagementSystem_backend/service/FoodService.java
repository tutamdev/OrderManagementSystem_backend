package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.FoodRequest;
import com.group19.OrderManagementSystem_backend.dto.response.FoodResponse;
import com.group19.OrderManagementSystem_backend.entity.Food;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.FoodMapper;
import com.group19.OrderManagementSystem_backend.repository.CategoryRepository;
import com.group19.OrderManagementSystem_backend.repository.FoodRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FoodService {

    FoodRepository foodRepository;
    CategoryRepository categoryRepository;
    FoodMapper foodMapper;

    public FoodResponse createFood(FoodRequest request) {
        var food = foodMapper.toFood(request);

        var category = categoryRepository.findByName(request.getCategory())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXITED));
        food.setCategory(category);

        food = foodRepository.save(food);
        return foodMapper.toFoodResponse(food);
    }

    public List<FoodResponse> getAll() {
        List<Food> foods = foodRepository.findAll();
        return foodMapper.toListFoodResponse(foods);
    }

    public List<FoodResponse> getAllFoodsByCategoryId(String categoryId) {
        List<Food> foods = foodRepository.findAllByCategory_CategoryId(categoryId);
        return foodMapper.toListFoodResponse(foods);
    }

    public FoodResponse updateFood(String foodId, FoodRequest foodRequest) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new AppException(ErrorCode.FOOD_NOT_EXITED));
        foodMapper.updateFood(food, foodRequest);
        return foodMapper.toFoodResponse(foodRepository.save(food));
    }

    public void deleteFood(String foodId) {
        foodRepository.deleteById(foodId);
    }
}
