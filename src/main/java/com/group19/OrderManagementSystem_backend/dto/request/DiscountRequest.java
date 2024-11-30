package com.group19.OrderManagementSystem_backend.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequest {
    private String discountCode;
    private String discountType;
    private double discountValue;
    private boolean status;
}
