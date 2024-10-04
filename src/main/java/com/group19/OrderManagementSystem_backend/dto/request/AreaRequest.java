package com.group19.OrderManagementSystem_backend.dto.request;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaRequest {
    private String areaName;
    private String description;
}
