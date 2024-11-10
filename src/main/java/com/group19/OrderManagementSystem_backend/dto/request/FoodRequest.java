package com.group19.OrderManagementSystem_backend.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {
    private String foodName;
    private long foodPrice;
    private String description;
    private String imgUrl;
    private boolean availability;
    private String category;
}
