package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.AreaRequest;
import com.group19.OrderManagementSystem_backend.dto.response.AreaResponse;
import com.group19.OrderManagementSystem_backend.entity.Area;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.AreaMapper;
import com.group19.OrderManagementSystem_backend.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService {

    @Autowired
    AreaRepository areaRepository;
    @Autowired
    AreaMapper areaMapper;

    public AreaResponse createArea(AreaRequest request) {
        if(areaRepository.existsByAreaName(request.getAreaName()))
            throw new AppException(ErrorCode.AREA_EXITED);
        Area area = areaRepository.save(areaMapper.toArea(request));
        return areaMapper.toAreaResponse(area);
    }

    public List<AreaResponse> getAllAreas() {
        List<Area> areas = areaRepository.findAll();
        return areaMapper.toListAreaResponse(areas);
    }

    public AreaResponse updateArea(String areaId, AreaRequest request) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new AppException(ErrorCode.AREA_NOT_EXITED));
        areaMapper.updateArea(area, request);
        return areaMapper.toAreaResponse(areaRepository.save(area));
    }

    public void deleteArea(String areaId) {
        areaRepository.deleteById(areaId);
    }
}
