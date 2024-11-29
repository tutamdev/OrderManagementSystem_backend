package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.OrderDetailRequest;
import com.group19.OrderManagementSystem_backend.dto.response.OrderDetailResponse;
import com.group19.OrderManagementSystem_backend.entity.Food;
import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.OrderDetail;
import com.group19.OrderManagementSystem_backend.entity.OrderDetailKey;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.OrderDetailMapper;
import com.group19.OrderManagementSystem_backend.repository.FoodRepository;
import com.group19.OrderManagementSystem_backend.repository.OrderDetailRepository;
import com.group19.OrderManagementSystem_backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private FoodRepository foodRepository;

    public List<OrderDetailResponse> createOrderDetail(String orderId, List<OrderDetailRequest> request) {
        List<OrderDetailResponse> response = new ArrayList<>();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        // Kiểm tra xem ca của order đã hoàn thành chưa
        if(order.getEndedAt() != null) throw new AppException(ErrorCode.ORDER_COMPLETED);

        // Kiểm tra xem ca của order hiện tại có đang mở không!
        if(order.getShift().isEnabled()) throw new AppException(ErrorCode.SHIFT_NOT_ACTIVE);

        // chỗ này thấy kì kì quá
        request.forEach(foodRequest -> {
            Food food = foodRepository.findByFoodId(foodRequest.getFoodId())
                    .orElseThrow(() -> new AppException(ErrorCode.FOOD_NOT_EXITED));

            // Nếu mà còn trong kho
            if(food.isAvailability()) {
                OrderDetailKey orderDetailKey = OrderDetailKey.builder()
                        .orderId(orderId)
                        .foodId(food.getFoodId())
                        .build();
                OrderDetail orderDetail = OrderDetail.builder()
                        .id(orderDetailKey)
                        .food(food)
                        .order(order)
                        .foodNote(foodRequest.getFoodNote())
                        .quantity(foodRequest.getQuantity())
                        .build();
                orderDetailRepository.save(orderDetail);
                response.add(orderDetailMapper.toOrderDetailResponse(orderDetail));
            } else throw new AppException(ErrorCode.FOOD_UNAVAILABLE);
        });
        return response;
    }

    public List<OrderDetailResponse> getOrderDetailsByOrderId(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_OrderId(orderId);
        return orderDetailMapper.toListOrderDetailResponse(orderDetails);
    }


    // Update chỗ này cũng thấy kì lạ, để lúc khác xem lại @@
    public List<OrderDetailResponse> updateOrderDetail(String orderId, List<OrderDetailRequest> request) {
        List<OrderDetailResponse> response = new ArrayList<>();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        // Kiểm tra xem ca của order đã hoàn thành chưa
        if(order.getEndedAt() != null) throw new AppException(ErrorCode.ORDER_COMPLETED);

        // Kiểm tra xem ca của order hiện tại có đang mở không!
        if(order.getShift().isEnabled()) throw new AppException(ErrorCode.SHIFT_NOT_ACTIVE);

        request.forEach(foodRequest -> {
            Food food = foodRepository.findByFoodId(foodRequest.getFoodId())
                    .orElseThrow(() -> new AppException(ErrorCode.FOOD_NOT_EXITED));

            // Nếu mà còn trong kho
            if(food.isAvailability()) {
                OrderDetailKey orderDetailKey = OrderDetailKey.builder()
                        .orderId(orderId)
                        .foodId(food.getFoodId())
                        .build();
                // Kiểm tra xem orderDetail đó có tồn tại
                OrderDetail orderDetail = OrderDetail.builder()
                        .id(orderDetailKey)
                        .food(food)
                        .order(order)
                        .foodNote(foodRequest.getFoodNote())
                        .quantity(foodRequest.getQuantity())
                        .build();
                orderDetailRepository.save(orderDetail);
                response.add(orderDetailMapper.toOrderDetailResponse(orderDetail));
            } else throw new AppException(ErrorCode.FOOD_UNAVAILABLE);
        });
        return response;
    }

    public void deleteOrderDetail(OrderDetailKey orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }
}
