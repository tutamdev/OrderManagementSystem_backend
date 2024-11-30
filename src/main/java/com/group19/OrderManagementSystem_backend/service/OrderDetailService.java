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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(!order.getShift().isEnabled()) throw new AppException(ErrorCode.SHIFT_NOT_ACTIVE);

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

    /**
     * Chỗ này là cập nhật order món
     * Nếu bàn cập nhật thêm món, nếu món đã tồn tại mà sửa thì tức là cập nhật quantity, note, nếu chưa thì tạo mới
     * Nếu quantity == 0 coi như là order món đó được xoá khỏi order
     *
     */

    public List<OrderDetailResponse> updateOrderDetail(String orderId, List<OrderDetailRequest> request) {
        List<OrderDetailResponse> response = new ArrayList<>();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));

        // Kiểm tra xem ca của order đã hoàn thành chưa
        if (order.getEndedAt() != null) throw new AppException(ErrorCode.ORDER_COMPLETED);

        // Kiểm tra xem ca của order hiện tại có đang mở không!
        if (!order.getShift().isEnabled()) throw new AppException(ErrorCode.SHIFT_NOT_ACTIVE);

        // Lấy danh sách các OrderDetail hiện có
        List<OrderDetail> existingDetails = orderDetailRepository.findByOrder_OrderId(orderId);

        // Map các OrderDetail hiện có bằng foodId để tiện truy xuất
        Map<String, OrderDetail> detailMap = existingDetails.stream()
                .collect(Collectors.toMap(detail -> detail.getFood().getFoodId(), detail -> detail));

        request.forEach(foodRequest -> {
            Food food = foodRepository.findByFoodId(foodRequest.getFoodId())
                    .orElseThrow(() -> new AppException(ErrorCode.FOOD_NOT_EXITED));

            // Nếu mà còn trong kho
            if (food.isAvailability()) {
                OrderDetail orderDetail = detailMap.get(foodRequest.getFoodId());

                if (orderDetail != null) {
                    if (foodRequest.getQuantity() == 0) {
                        // Xóa nếu quantity == 0
                        orderDetailRepository.delete(orderDetail);
                    } else {
                        // Cập nhật nếu tồn tại và quantity > 0
                        orderDetail.setQuantity(foodRequest.getQuantity());
                        orderDetail.setFoodNote(foodRequest.getFoodNote());
                        orderDetailRepository.save(orderDetail);
                        response.add(orderDetailMapper.toOrderDetailResponse(orderDetail));
                    }
                } else {
                    // Tạo mới nếu chưa tồn tại và quantity > 0
                    if (foodRequest.getQuantity() > 0) {
                        OrderDetailKey orderDetailKey = OrderDetailKey.builder()
                                .orderId(orderId)
                                .foodId(food.getFoodId())
                                .build();
                        orderDetail = OrderDetail.builder()
                                .id(orderDetailKey)
                                .food(food)
                                .order(order)
                                .foodNote(foodRequest.getFoodNote())
                                .quantity(foodRequest.getQuantity())
                                .build();
                        orderDetailRepository.save(orderDetail);
                        response.add(orderDetailMapper.toOrderDetailResponse(orderDetail));
                    }
                }
            } else throw new AppException(ErrorCode.FOOD_UNAVAILABLE);
        });

        return response;
    }

    public BigDecimal calculateTotalPrice(String orderId) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderDetail> existingDetails = orderDetailRepository.findByOrder_OrderId(orderId);

        if (existingDetails != null) {
            for (OrderDetail orderDetail : existingDetails) {
                if (orderDetail != null && orderDetail.getFood() != null) {
                    Food food = orderDetail.getFood();
                    BigDecimal foodPrice = food.getFoodPrice();
                    BigDecimal quantity = new BigDecimal(orderDetail.getQuantity());

                    // Tính tổng giá trị
                    totalPrice = totalPrice.add(foodPrice.multiply(quantity));
                }
            }
        }

        return totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP); // Làm tròn đến 2 chữ số thập phân
    }

//    public BigDecimal calculateTotalPriceFood(String orderId) {
//        BigDecimal totalPrice = BigDecimal.ZERO;
//        // Lấy danh sách các OrderDetail hiện có
//        List<OrderDetail> existingDetails = orderDetailRepository.findByOrder_OrderId(orderId);
//
//        if (existingDetails != null) {
//            existingDetails.forEach(orderDetail -> {
//               if (orderDetail != null && orderDetail.getFood() != null) {
//                   Food food = orderDetail.getFood();
//                   BigDecimal foodPrice = food.getFoodPrice();
//                   int quantity = orderDetail.getQuantity();
//                   totalPrice = totalPrice.add(foodPrice.multiply(BigDecimal.valueOf(quantity)));
//               }
//            });
//        }
//    }

    public void deleteOrderDetail(OrderDetailKey orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }
}
