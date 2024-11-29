package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.OrderRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.entity.*;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.OrderMapper;
import com.group19.OrderManagementSystem_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private DiscountRepository discountRepository;

    public OrderResponse createOrder(OrderRequest request) {
        // Tìm Employee theo employeeId
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        Shift shift = shiftRepository.findByShiftId(request.getShiftId())
                .orElseThrow(() -> new AppException(ErrorCode.SHIFT_NOT_EXITED));

        Table table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new AppException(ErrorCode.TABLE_NOT_EXITED));
//        Discount discount = discountRepository.findDiscountByDiscountCode(request.getDiscountCode());
//        Discount discount = discountRepository.findDiscountByDiscountCode(request.getDiscountCode());
        // Tạo Order từ request
//        Order order = orderMapper.toOrder(request);
        Order order = Order.builder()
                .employee(employee)
                .shift(shift)
                .table(table)
                .createdAt(LocalDateTime.now())
                .note(request.getNote())
//                .discount(discount)
                .build();
        // Lưu Order
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    public OrderResponse completeOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXITED));
        if (order.getEndedAt() != null) throw new AppException(ErrorCode.ORDER_COMPLETED);
        order.setEndedAt(LocalDateTime.now());
        // tính total price
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toOrderResponse(savedOrder);
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toListOrderResponse(orders);
    }

    public List<OrderResponse> getAllOrderByShiftId(String shiftId) {
        List<Order> orders = orderRepository.findAllByShift_ShiftId(shiftId);
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
