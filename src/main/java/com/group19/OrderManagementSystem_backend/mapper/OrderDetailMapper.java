package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.OrderDetailRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderDetailResponse;
import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    @Mapping(target = "order", source = "order") // ánh xạ order từ Order
    @Mapping(target = "quantity", source = "request.quantity")
    @Mapping(target = "foodNote", source = "request.foodNote") // Ghi chú món ăn
    OrderDetail toOrderDetail(OrderDetailRequest request, Order order);

    OrderDetailResponse toOrderDetailResponse(OrderDetail orderDetail);

    List<OrderDetailResponse> toListOrderDetailResponse(List<OrderDetail> orderDetails);

    void updateOrderDetail(@MappingTarget OrderDetail orderDetail, OrderDetailRequest request);
}
