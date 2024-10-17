package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.entity.Order_detail;
import com.group19.OrderManagementSystem_backend.entity.Order_detail_ID;
import com.group19.OrderManagementSystem_backend.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<Order_detail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public Optional<Order_detail> getOrderDetailById(Order_detail_ID id) {
        return orderDetailRepository.findById(id);
    }

    public Order_detail saveOrderDetail(Order_detail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(Order_detail_ID id) {
        orderDetailRepository.deleteById(id);
    }

    public List<Order_detail> getOrderDetailsByOrderId(String orderId) {
        return orderDetailRepository.findByOrder(orderId);
    }
}
