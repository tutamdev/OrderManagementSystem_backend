package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.ShiftRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.ShiftResponse;
import com.group19.OrderManagementSystem_backend.entity.Shift;
import com.group19.OrderManagementSystem_backend.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @GetMapping("/active")
    public ApiResponse<ShiftResponse> getActiveShifts() {
        return ApiResponse.<ShiftResponse>builder()
                .result(shiftService.findShiftByIsEnabled())
                .build();
    }

    @PostMapping("")
    public ApiResponse<ShiftResponse> createShift() {
        return ApiResponse.<ShiftResponse>builder()
                .result(shiftService.createShift())
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<ShiftResponse>> getAllShifts() {
        return ApiResponse.<List<ShiftResponse>>builder()
                .result(shiftService.findAllShifts())
                .build();
    }

    @GetMapping("/{date}")
    public ApiResponse<List<ShiftResponse>> getListShiftsByDate(@PathVariable("date") LocalDate date ) {
        return ApiResponse.<List<ShiftResponse>>builder()
                .result(shiftService.findShiftByDate(date))
                .build();
    }

    @PutMapping("/{shiftId}")
    public ApiResponse<ShiftResponse> updateShift(@PathVariable("shiftId") String shiftId) {
        return ApiResponse.<ShiftResponse>builder()
                .result(shiftService.closeShift(shiftId))
                .build();
    }
}
