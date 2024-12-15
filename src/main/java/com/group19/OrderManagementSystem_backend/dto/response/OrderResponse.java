package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String orderId;
    private String employeeId;
    private String employeeName;
    private String note;
    private double totalPrice;
    private LocalDateTime createdAt;
    private LocalDateTime endedAt;
    private String shiftId;
    private String areaId;
    private String areaName;
    private String tableId;
    private String tableName;
    private String discountCode;
    private double discountValue;
}
