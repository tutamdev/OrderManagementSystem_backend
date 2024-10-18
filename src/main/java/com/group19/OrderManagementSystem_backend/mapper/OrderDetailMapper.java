package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.OrderDetailRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderDetailResponse;
import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.Order_detail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(target = "order", source = "order") // ánh xạ order từ Order
    @Mapping(target = "quantity", source = "request.quantity")
    @Mapping(target = "food_note", source = "request.foodNote") // Ghi chú món ăn
    Order_detail toOrderDetail(OrderDetailRequest request, Order order);

    OrderDetailResponse toOrderDetailResponse(Order_detail orderDetail);

    List<OrderDetailResponse> toListOrderDetailResponse(List<Order_detail> orderDetails);

    void updateOrderDetail(@MappingTarget Order_detail orderDetail, OrderDetailRequest request);
}
