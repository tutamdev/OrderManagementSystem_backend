package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.OrderDetailRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.OrderDetailResponse;
import com.group19.OrderManagementSystem_backend.entity.OrderDetailKey;
import com.group19.OrderManagementSystem_backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-details")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> createOrderDetail(@PathVariable String orderId, @RequestBody List<OrderDetailRequest> orderDetailRequest) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.createOrderDetail(orderId, orderDetailRequest))
                .message("Order detail created successfully")
                .build();
    }

    @GetMapping("/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetailsByOrderId(@PathVariable String orderId) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getOrderDetailsByOrderId(orderId))
                .message("Successfully retrieved order details")
                .build();
    }

    @PutMapping("/{orderId}")
    public ApiResponse<List<OrderDetailResponse>> updateOrderDetail(@PathVariable("orderId") String orderId, @RequestBody List<OrderDetailRequest> orderDetailRequest) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.updateOrderDetail(orderId, orderDetailRequest))
                .message("Order detail updated successfully")
                .build();
    }

    @DeleteMapping("/{orderDetailId}")
    public ApiResponse<Void> deleteOrderDetail(@PathVariable("orderDetailId") OrderDetailKey orderDetailId) {
        orderDetailService.deleteOrderDetail(orderDetailId);
        return ApiResponse.<Void>builder()
                .message("Order detail deleted successfully")
                .build();
    }
}
