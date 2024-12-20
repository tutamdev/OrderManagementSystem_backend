package com.group19.OrderManagementSystem_backend.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private String foodId; // ID của Order để ánh xạ
    private int quantity;
    private String foodNote; // Ghi chú về món ăn

}
