package com.group19.OrderManagementSystem_backend.controller;

import com.group19.OrderManagementSystem_backend.dto.request.TableRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ApiResponse;
import com.group19.OrderManagementSystem_backend.dto.response.TableResponse;
import com.group19.OrderManagementSystem_backend.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/area/{areaId}")
    public ApiResponse<List<TableResponse>> getTablesByAreaId(@PathVariable("areaId") String areaId) {
        List<TableResponse> tables = tableService.getTablesByAreaId(areaId);
        return ApiResponse.<List<TableResponse>>builder()
                .result(tables)
                .build();
    }

    @PostMapping("/area/{areaId}")
    public ApiResponse<List<TableResponse>> createTableByAreaId(@PathVariable("areaId") String areaId, @RequestBody TableRequest tableRequest) {
        List<TableResponse> tables = tableService.createTableByAreaId(areaId, tableRequest);
        return ApiResponse.<List<TableResponse>>builder()
                .result(tables)
                .build();
    }

    @PutMapping("/{tableId}")
    public ApiResponse<TableResponse> updateTable(@PathVariable("tableId") String tableId, @RequestBody TableRequest tableRequest) {
        return ApiResponse.<TableResponse>builder()
                .result(tableService.updateTable(tableId, tableRequest))
                .build();
    }

    @DeleteMapping("/{tableId}")
    public ApiResponse<Void> deleteTable(@PathVariable("tableId") String tableId) {
        tableService.DeleteTable(tableId);
        return ApiResponse.<Void>builder()
                .message("Delete table successfully")
                .build();
    }
}
