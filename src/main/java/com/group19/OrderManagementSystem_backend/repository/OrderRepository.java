package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByEmployee_Id(String id);//tìm theo người tạo

    List<Order> findAllByShift_ShiftId(String shiftId);

    List<Order> findAllByShift_ShiftIdAndEndedAtIsNotNull(String shiftId);

    Order findOrdersByTable_TableIdAndShift_ShiftIdAndEndedAtIsNull(String tableId, String shiftId);
}
