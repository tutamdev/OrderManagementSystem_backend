package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.DiscountRequest;
import com.group19.OrderManagementSystem_backend.dto.response.DiscountResponse;
import com.group19.OrderManagementSystem_backend.entity.Discount;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.DiscountMapper;
import com.group19.OrderManagementSystem_backend.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private DiscountMapper discountMapper;

    public DiscountResponse createDiscount(DiscountRequest discountRequest) {
        if (discountRepository.existsById(discountRequest.getDiscountCode()))
            throw new AppException(ErrorCode.DISCOUNT_EXITED);
        Discount discount = discountRepository.save(discountMapper.toDiscount(discountRequest));
        return discountMapper.toDiscountResponse(discount);
    }

    public DiscountResponse getDiscountByDiscountCode(String discountCode) {
        Discount discount = discountRepository.findById(discountCode)
                .orElseThrow(() -> new AppException(ErrorCode.DISCOUNT_NOT_EXITED));
        return discountMapper.toDiscountResponse(discount);
    }

    public List<DiscountResponse> getAllDiscount() {
        List<Discount> discounts = discountRepository.findAll();
        return discountMapper.toDiscountResponseList(discounts);
    }

    public DiscountResponse updateDiscount(DiscountRequest discountRequest) {
        Discount discount = discountRepository.findById(discountRequest.getDiscountCode())
                .orElseThrow(() -> new AppException(ErrorCode.DISCOUNT_NOT_EXITED));
        Discount discountUpdate = discountRepository.save(discountMapper.toDiscount(discountRequest));
        return discountMapper.toDiscountResponse(discountUpdate);
    }

    public void deleteDiscount(String discountCode) {
        Discount discount = discountRepository.findById(discountCode)
                .orElseThrow(() -> new AppException(ErrorCode.DISCOUNT_NOT_EXITED));
        discountRepository.delete(discount);
    }
}
