package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.entity.OrderDetail;
import com.group19.OrderManagementSystem_backend.entity.OrderDetailKey;
import com.group19.OrderManagementSystem_backend.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetails() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail getOrderDetailById(OrderDetailKey id) {
        return orderDetailRepository.findById(id).orElseThrow();
    }

    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteOrderDetail(OrderDetailKey id) {
        orderDetailRepository.deleteById(id);
    }

//    public List<OrderDetail> getOrderDetailsByOrderId(String orderId) {
//        return orderDetailRepository.findByOrder(orderId);
//    }
}
