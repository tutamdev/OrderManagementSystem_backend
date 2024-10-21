package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private String orderDetailId; // ID của Order Detail
    private String orderId; // ID của Order
    private int quantity;
    private String foodNote; // Ghi chú về món ăn
}
