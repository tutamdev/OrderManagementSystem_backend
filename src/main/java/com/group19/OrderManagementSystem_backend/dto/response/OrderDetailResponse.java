package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {
    private String orderId;
    private String foodId;
    private String foodName;
    private int quantity;
    private String foodNote;
    private BigDecimal foodPrice;
}
