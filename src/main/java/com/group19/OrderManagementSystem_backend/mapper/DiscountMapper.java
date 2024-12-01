package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.DiscountRequest;
import com.group19.OrderManagementSystem_backend.dto.response.DiscountResponse;
import com.group19.OrderManagementSystem_backend.entity.Discount;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiscountMapper {
    Discount toDiscount(DiscountRequest discountRequest);
    DiscountResponse toDiscountResponse(Discount discount);
    List<DiscountResponse> toDiscountResponseList(List<Discount> discountList);
}
