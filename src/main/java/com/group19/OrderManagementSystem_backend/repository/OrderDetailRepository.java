package com.group19.OrderManagementSystem_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.OrderDetail;
import com.group19.OrderManagementSystem_backend.entity.OrderDetailId;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

	List<OrderDetail> findByOrder(Order order);
	List<OrderDetail> findByOrder_OrderId(String orderId);
}
