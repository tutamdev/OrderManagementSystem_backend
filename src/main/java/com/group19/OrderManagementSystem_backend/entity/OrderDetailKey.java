package com.group19.OrderManagementSystem_backend.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailKey implements Serializable {
    @Column(name = "order_id")
	private String orderId;
    @Column(name = "food_id")
	private String foodId;
}
