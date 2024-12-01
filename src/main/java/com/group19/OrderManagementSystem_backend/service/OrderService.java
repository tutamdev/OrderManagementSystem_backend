package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.OrderRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderResponse;
import com.group19.OrderManagementSystem_backend.entity.*;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.OrderMapper;
import com.group19.OrderManagementSystem_backend.repository.*;
import com.group19.OrderManagementSystem_backend.utils.DiscountType;
import com.group19.OrderManagementSystem_backend.utils.TableStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Slf4j
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
    @Autowired
    private OrderDetailService orderDetailService;

    public OrderResponse createOrder(OrderRequest request) {
        // Tìm Employee theo employeeId
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        Shift shift = shiftRepository.findByIsEnabledTrue()
                .orElseThrow(() -> new AppException(ErrorCode.SHIFT_NOT_EXITED));

        Table table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new AppException(ErrorCode.TABLE_NOT_EXITED));
        Discount discount = null;
        if (request.getDiscountCode() != null && !request.getDiscountCode().isEmpty()) {
            discount = discountRepository.findDiscountByDiscountCodeAndStatusIsTrue(request.getDiscountCode())
                    .orElseThrow(() -> new AppException(ErrorCode.DISCOUNT_NOT_EXITED));
        }
        log.info(request.getDiscountCode());

        // Nếu table trống thì mới cho đặt -> cập nhật trạng thái table <Làm sau>
        if (!Objects.equals(table.getStatus(), TableStatus.AVAILABLE.name()))
            throw  new AppException(ErrorCode.TABLE_UNAVAILABLE);

        Order order = Order.builder()
                .employee(employee)
                .shift(shift)
                .table(table)
                .createdAt(LocalDateTime.now())
                .note(request.getNote())
                .discount(discount)
                .build();
        // Lưu Order
        Order savedOrder = orderRepository.save(order);

        // Cập nhật trạng thái bàn ăn
        table.setStatus(TableStatus.UNAVAILABLE.name());
        tableRepository.save(table);

        return orderMapper.toOrderResponse(savedOrder);
    }

    public OrderResponse updateOrder(String orderId, OrderRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        // Employee tạo order sẽ không được cập nhật mới, chỉ note được cập nhật
        // Cập nhật Order
        Table table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new AppException(ErrorCode.TABLE_UNAVAILABLE));
        // chức năng chuyển bàn
        order.setTable(table);
        table.setStatus(TableStatus.UNAVAILABLE.name());
        tableRepository.save(table);
        Discount discount = null;
        if (request.getDiscountCode() != null && !request.getDiscountCode().isEmpty()) {
            discount = discountRepository.findDiscountByDiscountCodeAndStatusIsTrue(request.getDiscountCode())
                    .orElseThrow(() -> new AppException(ErrorCode.DISCOUNT_NOT_EXITED));
        }
        order.setDiscount(discount);
        order.setNote(request.getNote());
        // Lưu lại Order đã cập nhật
        return orderMapper.toOrderResponse(orderRepository.save(order));
    }

    public OrderResponse completeOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXITED));
        Table table = order.getTable();

        if (order.getEndedAt() != null) throw new AppException(ErrorCode.ORDER_COMPLETED);
        // thời gian hoàn thành order
        order.setEndedAt(LocalDateTime.now());
        // tính total price food
        double totalPrice = orderDetailService.calculateTotalPrice(orderId).doubleValue();
        order.setTotalPrice(totalPrice);
        // Discount value
        double discountValue = 0;
        if (order.getDiscount() == null) discountValue = 0;
        else if (Objects.equals(order.getDiscount().getDiscountType(), DiscountType.FIXED.name()))
            discountValue = order.getDiscount().getDiscountValue();
        else if (Objects.equals(order.getDiscount().getDiscountType(), DiscountType.PERCENT.name()))
            discountValue = (totalPrice * order.getDiscount().getDiscountValue())/100;
        Order savedOrder = orderRepository.save(order);

        // Cập nhật trạng thái bàn ăn
        table.setStatus(TableStatus.AVAILABLE.name());
        tableRepository.save(table);

        OrderResponse orderResponse = orderMapper.toOrderResponse(savedOrder);
        orderResponse.setDiscountValue(discountValue);
        return orderResponse;
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toListOrderResponse(orders);
    }

    public List<OrderResponse> getAllOrderByShiftId(String shiftId) {
        List<Order> orders = orderRepository.findAllByShift_ShiftId(shiftId);
        return orderMapper.toListOrderResponse(orders);
    }

    public OrderResponse getOrderByOrderId(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        return orderMapper.toOrderResponse(order);
    }

    public List<OrderResponse> getAllOrderByShiftIdCompleted(String shiftId) {
        List<Order> orders = orderRepository.findAllByShift_ShiftIdAndEndedAtIsNotNull(shiftId);
        return orderMapper.toListOrderResponse(orders);
    }



    public void deleteOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXITED));
        Table table = order.getTable();
        table.setStatus(TableStatus.AVAILABLE.name());
        tableRepository.save(table);
        orderRepository.deleteById(orderId);
    }
}
