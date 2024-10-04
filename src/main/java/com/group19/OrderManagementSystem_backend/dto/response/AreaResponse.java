package com.group19.OrderManagementSystem_backend.dto.response;

import com.group19.OrderManagementSystem_backend.entity.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaResponse {
    private String areaId;
    private String areaName;
    private String description;
}
