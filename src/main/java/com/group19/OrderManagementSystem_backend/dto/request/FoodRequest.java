package com.group19.OrderManagementSystem_backend.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {
    private String foodName;
    private long foodPrice;
    private String description;
    private String imageUrl;
    private boolean availability;
    private String category;
}
