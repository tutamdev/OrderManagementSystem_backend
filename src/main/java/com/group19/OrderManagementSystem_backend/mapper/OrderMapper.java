package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.OrderRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "employee.id", source = "id")
    Order toOrder(OrderRequest orderRequest);

    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toListOrderResponse(List<Order> orders);

    void updateOrder(@MappingTarget Order order, OrderRequest orderRequest);
}
