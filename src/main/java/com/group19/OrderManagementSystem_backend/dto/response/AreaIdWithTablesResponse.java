package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaIdWithTablesResponse {
    String areaId;
    List<TableResponse> tables;
}
