package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.CategoryRequest;
import com.group19.OrderManagementSystem_backend.dto.response.CategoryResponse;
import com.group19.OrderManagementSystem_backend.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "name", source = "name")
    Category toCategory(CategoryRequest categoryRequest);

    @Mapping(source = "name", target = "name")
    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toListCategoryResponses(List<Category> categories);

    void updateCategory(@MappingTarget Category category, CategoryRequest categoryRequest);
}
