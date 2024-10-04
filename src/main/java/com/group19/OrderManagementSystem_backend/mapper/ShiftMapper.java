package com.group19.OrderManagementSystem_backend.mapper;

import com.group19.OrderManagementSystem_backend.dto.request.ShiftRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ShiftResponse;
import com.group19.OrderManagementSystem_backend.entity.Shift;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ShiftMapper {
    Shift toShift(ShiftRequest shiftRequest);
    default ShiftResponse toShiftResponse(Shift shift) {
        if (shift == null) {
            return null;
        }
        ShiftResponse response = new ShiftResponse();
        response.setShiftId(shift.getShiftId());
        response.setDate(shift.getDate());
        response.setStartTime(shift.getStartTime());
        response.setEndTime(shift.getEndTime());
        response.setEnabled(shift.isEnabled());
        return response;
    }
    List<ShiftResponse> toListShiftResponse(List<Shift> shifts);
}
