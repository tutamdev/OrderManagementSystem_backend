package com.group19.OrderManagementSystem_backend.entity;

import com.group19.OrderManagementSystem_backend.utils.ERole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
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
	private OrderDetailId order_detail_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="food_note")
	private String foodNote;
}
