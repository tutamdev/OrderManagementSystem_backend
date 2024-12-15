package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.DiscountRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.DiscountResponse;
import com.group19.OrderManagementSystem_backend.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @GetMapping("")
    public ApiResponse<List<DiscountResponse>> getAllDiscount() {
        return ApiResponse.<List<DiscountResponse>>builder()
                .message("Success")
                .result(discountService.getAllDiscount())
                .build();
    }

    @GetMapping("/{discountCode}")
    public ApiResponse<DiscountResponse> getDiscountByDiscountCode(@PathVariable String discountCode) {
        return ApiResponse.<DiscountResponse>builder()
                .message("Success")
                .result(discountService.getDiscountByDiscountCode(discountCode))
                .build();
    }

    @PostMapping("")
    public ApiResponse<DiscountResponse> createDiscount(@RequestBody DiscountRequest discountRequest) {
        return ApiResponse.<DiscountResponse>builder()
                .message("Success")
                .result(discountService.createDiscount(discountRequest))
                .build();
    }

    @PutMapping("")
    public ApiResponse<DiscountResponse> updateDiscount(@RequestBody DiscountRequest discountRequest) {
        return ApiResponse.<DiscountResponse>builder()
                .message("Success")
                .result(discountService.updateDiscount(discountRequest))
                .build();
    }
//    @DeleteMapping("/{discountCode}")
//    public ApiResponse<DiscountResponse> deleteDiscount(@PathVariable String discountCode) {
//        discountService.deleteDiscount(discountCode);
//        return ApiResponse.<DiscountResponse>builder()
//                .message("Success")
//                .build();
//    }
}
