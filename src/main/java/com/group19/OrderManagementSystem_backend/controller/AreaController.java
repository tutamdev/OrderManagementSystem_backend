package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.AreaRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.AreaResponse;
import com.group19.OrderManagementSystem_backend.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    @Autowired
    AreaService areaService;

    @PostMapping("")
    public ApiResponse<AreaResponse> createArea(@RequestBody AreaRequest areaRequest) {
        return ApiResponse.<AreaResponse>builder()
                .result(areaService.createArea(areaRequest))
                .message("Create area successfully")
                .build();
    }

    @GetMapping("")
    public ApiResponse<List<AreaResponse>> getAllAreas() {
        return ApiResponse.<List<AreaResponse>>builder()
                .result(areaService.getAllAreas())
                .message("Successfully retrieved all areas")
                .build();
    }

    @PutMapping("/{areaId}")
    public ApiResponse<AreaResponse> updateArea(@PathVariable("areaId") String areaId, @RequestBody AreaRequest areaRequest) {
        return ApiResponse.<AreaResponse>builder()
                .result(areaService.updateArea(areaId, areaRequest))
                .message("Successfully retrieved all areas")
                .build();
    }

    @DeleteMapping("/{areaId}")
    public ApiResponse<Void> deleteArea(@PathVariable String areaId) {
        areaService.deleteArea(areaId);
        return ApiResponse.<Void>builder()
                .message("Delete area successfully")
                .build();
    }
}
