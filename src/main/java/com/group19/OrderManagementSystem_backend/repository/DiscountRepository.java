package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountRepository extends JpaRepository<Discount, String> {
    Discount getDiscountByDiscountCode(String discount);

    Optional<Discount> findDiscountByDiscountCodeAndStatusIsTrue(String discountCode);

    Discount findDiscountByDiscountCode(String discountCode);
}
