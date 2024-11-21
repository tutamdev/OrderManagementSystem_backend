package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodResponse {
    private String foodId;
    private String foodName;
    private long foodPrice;
    private String description;
    private String imageUrl;
    private boolean availability;
    private CategoryResponse category;
}
