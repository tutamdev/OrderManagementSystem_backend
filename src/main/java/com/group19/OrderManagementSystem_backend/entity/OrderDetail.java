package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_order_detail")

public class OrderDetail {
    @EmbeddedId
    private OrderDetailKey id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("foodId")
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "food_note")
    private String foodNote;
}
