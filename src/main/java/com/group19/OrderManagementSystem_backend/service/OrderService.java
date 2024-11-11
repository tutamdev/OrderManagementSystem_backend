package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.OrderRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.Employee;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.OrderMapper;
import com.group19.OrderManagementSystem_backend.repository.OrderRepository;
import com.group19.OrderManagementSystem_backend.repository.EmployeeRepository; // Thêm import này
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private OrderMapper orderMapper;

    public OrderResponse createOrder(OrderRequest request) {
        // Tìm Employee theo employeeId
        Employee employee = employeeRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        // Tạo Order từ request
        Order order = orderMapper.toOrder(request);
        order.setEmployee(employee); // Thiết lập employee cho order

        // Lưu Order
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toListOrderResponse(orders);
    }

    public OrderResponse updateOrder(String orderId, OrderRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        // Cập nhật Order
        orderMapper.updateOrder(order, request);

        // Lưu lại Order đã cập nhật
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    public void deleteOrder(String orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }
        orderRepository.deleteById(orderId);
    }
}
