package com.group19.OrderManagementSystem_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group19.OrderManagementSystem_backend.entity.Order;
import com.group19.OrderManagementSystem_backend.entity.Order_detail;
import com.group19.OrderManagementSystem_backend.entity.Order_detail_ID;

@Repository
public interface OrderDetailRepository extends JpaRepository<Order_detail, Order_detail_ID> {
	//List<Order_detail> findByOrder(String order_id);
}
