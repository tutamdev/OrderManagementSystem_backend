package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,String> {
    Discount getDiscountByDiscountCode(String discount);

    Discount findDiscountByDiscountCode(String discountCode);
}
