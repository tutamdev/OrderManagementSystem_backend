package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {
    private String discountCode;
    private String discountType;
    private double discountValue;
    private boolean status;
}
