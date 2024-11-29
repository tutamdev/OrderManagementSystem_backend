package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String orderId;
    private String note;
    private double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;
    private String employeeId;
    private String shiftId;
//    private String discountCode;
}
