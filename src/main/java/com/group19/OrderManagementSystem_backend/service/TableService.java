package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.TableRequest;
import com.group19.OrderManagementSystem_backend.dto.response.AreaIdWithTablesResponse;
import com.group19.OrderManagementSystem_backend.dto.response.TableResponse;
import com.group19.OrderManagementSystem_backend.entity.Area;
import com.group19.OrderManagementSystem_backend.entity.Table;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.TableMapper;
import com.group19.OrderManagementSystem_backend.repository.AreaRepository;
import com.group19.OrderManagementSystem_backend.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;
    @Autowired
    AreaRepository areaRepository;
    @Autowired
    TableMapper tableMapper;

    public List<TableResponse> getTablesByAreaId(String areaId) {
        List<Table> tables = tableRepository.findByArea_AreaIdOrderByTableNameAsc(areaId);
        return tableMapper.toListTableResponse(tables);
    }

    public List<AreaIdWithTablesResponse> getAllAreaIdWithTables() {
        List<AreaIdWithTablesResponse> list = new ArrayList<>();
        List<Area> areas = areaRepository.findAll();
        for (Area area : areas) {
            List<Table> tables = tableRepository.findByArea_AreaIdOrderByTableNameAsc(area.getAreaId());
            AreaIdWithTablesResponse areaIdWithTablesResponse = AreaIdWithTablesResponse.builder()
                    .areaId(area.getAreaId())
                    .tables(tableMapper.toListTableResponse(tables))
                    .build();
            list.add(areaIdWithTablesResponse);
        }
        return list;
    }

    public List<TableResponse> createTableByAreaId(String areaId, TableRequest tableRequest) {
        Area area = areaRepository.findById(areaId).get();
        if (area != null) {
            Table table = Table.builder()
                    .tableName(tableRequest.getTableName())
                    .description(tableRequest.getDescription())
                    .status(tableRequest.getStatus())
                    .area(area)
                    .build();
            tableRepository.save(table);
        }
        List<Table> tables = tableRepository.findByArea_AreaIdOrderByTableNameAsc(areaId);
        return tableMapper.toListTableResponse(tables);
    }

    public TableResponse updateTable(String tableId, TableRequest tableRequest) {
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new AppException(ErrorCode.TABLE_NOT_EXITED));
        tableMapper.updateTable(table, tableRequest);
        return tableMapper.toTableResponse(tableRepository.save(table));
    }

    public void DeleteTable(String tableId) {
        tableRepository.deleteById(tableId);
    }
}
