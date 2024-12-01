package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.OrderDetailRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderDetailResponse;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

//    @Mapping(target = "order", source = "order") // ánh xạ order từ Order
//    @Mapping(target = "quantity", source = "request.quantity")
//    @Mapping(target = "foodNote", source = "request.foodNote") // Ghi chú món ăn
    OrderDetail toOrderDetail(OrderDetailRequest request, Order order);

    default OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail) {
        if (orderDetail == null) {
            return null;
        }
        OrderDetailResponse response = OrderDetailResponse.builder()
                .orderId(orderDetail.getId().getOrderId())
                .foodId(orderDetail.getId().getFoodId())
                .quantity(orderDetail.getQuantity())
                .foodNote(orderDetail.getFoodNote())
                .foodName(orderDetail.getFood().getFoodName())
                .foodPrice(orderDetail.getFood().getFoodPrice())
                .build();
        return response;
    }

    default List<OrderDetailResponse> toListOrderDetailResponse(List<OrderDetail> orderDetails) {
        if (orderDetails == null || orderDetails.isEmpty()) {
            return Collections.emptyList();
        }
        return orderDetails.stream()
                .map(this::toOrderDetailResponse)
                .collect(Collectors.toList());
    }

    void updateOrderDetail(@MappingTarget OrderDetail orderDetail, OrderDetailRequest request);
}
