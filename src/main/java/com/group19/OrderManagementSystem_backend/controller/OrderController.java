package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.OrderRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

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
    // Tạo mới Order
    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderByOrderId(@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.getOrderByOrderId(orderId))
                .message("Order created successfully")
                .build();
    }


    // Lấy danh sách tất cả các Order theo ShiftId
    @GetMapping("/shift/{shiftId}")
    public ApiResponse<List<OrderResponse>> getAllOrderByShiftId(@PathVariable String shiftId) {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrderByShiftId(shiftId))
                .message("Successfully retrieved all orders")
                .build();
    }

    @GetMapping("/shift/{shiftId}/complete")
    public ApiResponse<List<OrderResponse>> getAllOrderByShiftIdCompleted(@PathVariable String shiftId) {
        return ApiResponse.<List<OrderResponse>>builder()
                .result(orderService.getAllOrderByShiftIdCompleted(shiftId))
                .message("Successfully retrieved all orders")
                .build();
    }

    // Hoan thanh order
    @PostMapping("/{orderId}/complete")
    public ApiResponse<OrderResponse> createOrder(@PathVariable String orderId) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.completeOrder(orderId))
                .message("Order created successfully")
                .build();
    }

    // Cập nhật Order theo ID
    @PutMapping("/{orderId}")
    public ApiResponse<OrderResponse> updateOrder(
            @PathVariable String orderId,
            @RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateOrder(orderId, orderRequest))
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
