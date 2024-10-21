package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.OrderDetailRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderDetailResponse;
import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.OrderDetail;
import com.group19.OrderManagementSystem_backend.entity.OrderDetailId;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.OrderDetailMapper;
import com.group19.OrderManagementSystem_backend.repository.OrderDetailRepository;
import com.group19.OrderManagementSystem_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    public OrderDetailResponse createOrderDetail(OrderDetailRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND)); // Thay đổi theo logic của bạn

        OrderDetail orderDetail = orderDetailMapper.toOrderDetail(request, order);
        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(orderDetail);
    }


    public List<OrderDetailResponse> getOrderDetailsByOrderId(String order_id) {
        Order order = orderRepository.findById(order_id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_OrderId(order_id);
        return orderDetailMapper.toListOrderDetailResponse(orderDetails);
    }

    public OrderDetailResponse updateOrderDetail(OrderDetailId orderDetailId, OrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_DETAIL_NOT_FOUND));

        orderDetailMapper.updateOrderDetail(orderDetail, request);
        OrderDetail updatedOrderDetail = orderDetailRepository.save(orderDetail);
        return orderDetailMapper.toOrderDetailResponse(updatedOrderDetail);
    }

    public void deleteOrderDetail(OrderDetailId orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }
}
