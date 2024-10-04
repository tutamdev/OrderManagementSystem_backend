package com.group19.OrderManagementSystem_backend.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TableResponse {
    private String tableId;
    private String tableName;
    private String description;
    private String status;
}
