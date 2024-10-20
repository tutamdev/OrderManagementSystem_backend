package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private String categoryId;
    private String categoryName;
}
