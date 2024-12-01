package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
	
	List<Order> findByEmployee_Id(String id);//tìm theo người tạo

    List<Order> findAllByShift_ShiftId(String shiftId);

    List<Order> findAllByShift_ShiftIdAndEndedAtIsNotNull(String shiftId);
}
