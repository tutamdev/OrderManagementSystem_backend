package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.AreaRequest;
import com.group19.OrderManagementSystem_backend.dto.response.AreaResponse;
import com.group19.OrderManagementSystem_backend.entity.Area;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AreaMapper {
    Area toArea(AreaRequest areaRequest);
    AreaResponse toAreaResponse(Area area);
    List<AreaResponse> toListAreaResponse(List<Area> areas);
    void updateArea(@MappingTarget Area area, AreaRequest areaRequest);
}
