package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.entity.OrderDetail;
import com.group19.OrderManagementSystem_backend.entity.OrderDetailKey;
import com.group19.OrderManagementSystem_backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping
    public ApiResponse<List<OrderDetail>> getAllOrderDetails() {
        return ApiResponse.<List<OrderDetail>>builder()
                .result(orderDetailService.getAllOrderDetails())
                .build();
    }

    @GetMapping("/{orderId}/{foodId}")
    public ApiResponse<OrderDetail> getOrderDetailById(@PathVariable("orderId") String orderId, @PathVariable("foodId") String foodId) {
        OrderDetailKey id = OrderDetailKey.builder()
                .orderId(orderId)
                .foodId(foodId)
                .build();
        return ApiResponse.<OrderDetail>builder()
                .result(orderDetailService.getOrderDetailById(id))
                .build();
    }

    @PostMapping
    public ApiResponse<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail) {
        return ApiResponse.<OrderDetail>builder()
                .result(orderDetailService.saveOrderDetail(orderDetail))
                .build();
    }

    @DeleteMapping("/{orderId}/{foodId}")
    public ApiResponse<Void> deleteOrderDetail(@PathVariable("orderId") String orderId, @PathVariable("foodId") String foodId) {
        OrderDetailKey id = OrderDetailKey.builder()
                .orderId(orderId)
                .foodId(foodId)
                .build();
        orderDetailService.deleteOrderDetail(id);
        return ApiResponse.<Void>builder()
                .message("OrderDetail deleted")
                .build();
    }
}
