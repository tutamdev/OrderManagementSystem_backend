package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.OrderDetailRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.OrderDetailResponse;
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

    @PostMapping("")
    public ApiResponse<OrderDetailResponse> createOrderDetail(@RequestBody OrderDetailRequest orderDetailRequest) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.createOrderDetail(orderDetailRequest))
                .message("Order detail created successfully")
                .build();
    }

    @GetMapping("/order/{order_id}")
    public ApiResponse<List<OrderDetailResponse>> getOrderDetailsByOrderId(@PathVariable("order_id") String order_id) {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .result(orderDetailService.getOrderDetailsByOrderId(order_id))
                .message("Successfully retrieved order details")
                .build();
    }

    @PutMapping("/{orderDetailId}")
    public ApiResponse<OrderDetailResponse> updateOrderDetail(@PathVariable("orderDetailId") OrderDetailKey orderDetailId, @RequestBody OrderDetailRequest orderDetailRequest) {
        return ApiResponse.<OrderDetailResponse>builder()
                .result(orderDetailService.updateOrderDetail(orderDetailId, orderDetailRequest))
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
