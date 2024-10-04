package com.group19.OrderManagementSystem_backend.mapper;



import com.group19.OrderManagementSystem_backend.dto.request.TableRequest;
import com.group19.OrderManagementSystem_backend.dto.response.TableResponse;
import com.group19.OrderManagementSystem_backend.entity.Table;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableResponse toTableResponse(Table table);
    List<TableResponse> toListTableResponse(List<Table> tables);
    void updateTable(@MappingTarget Table table, TableRequest tableRequest);
}
