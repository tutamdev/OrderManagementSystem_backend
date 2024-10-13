package com.group19.OrderManagementSystem_backend.entity;

import java.io.Serializable;
import lombok.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order_detail_ID implements Serializable {
	private String order_id;
	private String food_id;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order_detail_ID that = (Order_detail_ID) o;
        return Objects.equals(order_id, that.order_id) && Objects.equals(food_id, that.food_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id, food_id);
    }
}
