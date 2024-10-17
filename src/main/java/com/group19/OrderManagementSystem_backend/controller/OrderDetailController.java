package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.entity.Order_detail;
import com.group19.OrderManagementSystem_backend.entity.Order_detail_ID;
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
    public List<Order_detail> getAllOrderDetails() {
        return orderDetailService.getAllOrderDetails();
    }

    @GetMapping("/{orderId}/{productId}")
    public ResponseEntity<Order_detail> getOrderDetailById(@PathVariable("orderId") String orderId, @PathVariable("productId") String productId) {
        Order_detail_ID id = new Order_detail_ID(orderId, productId);
        return orderDetailService.getOrderDetailById(id)
                .map(orderDetail -> ResponseEntity.ok(orderDetail))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Order_detail createOrderDetail(@RequestBody Order_detail orderDetail) {
        return orderDetailService.saveOrderDetail(orderDetail);
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("orderId") String orderId, @PathVariable("productId") String productId) {
        Order_detail_ID id = new Order_detail_ID(orderId, productId);
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.noContent().build();
    }
}
