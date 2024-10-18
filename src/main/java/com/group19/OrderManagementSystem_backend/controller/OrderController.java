package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.OrderRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    // Tạo mới Order
    @PostMapping("")
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(orderRequest))
                .message("Order created successfully")
                .build();
    }

    // Lấy danh sách tất cả các Order
    @GetMapping("")
    public ApiResponse<List<OrderResponse>> getAllOrders() {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrders())
                .message("Successfully retrieved all orders")
                .build();
    }

    // Cập nhật Order theo ID
    @PutMapping("/{order_id}")
    public ApiResponse<OrderResponse> updateOrder(
            @PathVariable String order_id,
            @RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateOrder(order_id, orderRequest))
                .message("Order updated successfully")
                .build();
    }

    // Xóa Order theo ID
    @DeleteMapping("/{order_id}")
    public ApiResponse<Void> deleteOrder(@PathVariable String order_id) {
        orderService.deleteOrder(order_id);
        return ApiResponse.<Void>builder()
                .message("Order deleted successfully")
                .build();
    }
}
