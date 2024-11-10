package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String order_id;
    private String note;
    private double totalPrice;
    private String createdAt;
    private String endedAt;
    private String userName;
    private String shiftId;
}
