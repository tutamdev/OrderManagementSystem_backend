package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.OrderRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    //    @Mapping(target = "employee.id", source = "id")
    Order toOrder(OrderRequest orderRequest);

    default OrderResponse toOrderResponse(Order order) {
        if (order == null) {
            return null;
        }
        OrderResponse response = OrderResponse.builder()
                .orderId(order.getOrderId())
                .shiftId(order.getShift().getShiftId())
                .areaId(order.getTable().getTableId())
                .areaName(order.getTable().getArea().getAreaName())
                .tableId(order.getTable().getTableId())
                .tableName(order.getTable().getTableName())
                .note(order.getNote())
                .createdAt(order.getCreatedAt())
                .endedAt(order.getEndedAt())
                .totalPrice(order.getTotalPrice())
                .employeeId(order.getEmployee().getId())
                .employeeName(order.getEmployee().getFullName())
                .discountCode(order.getDiscount() != null ? order.getDiscount().getDiscountCode() : null)
                .build();

        return response;
    }

    List<OrderResponse> toListOrderResponse(List<Order> orders);

    void updateOrder(@MappingTarget Order order, OrderRequest orderRequest);
}
