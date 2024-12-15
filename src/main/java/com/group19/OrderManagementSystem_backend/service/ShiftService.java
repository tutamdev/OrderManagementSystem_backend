package com.group19.OrderManagementSystem_backend.service;

import com.group19.OrderManagementSystem_backend.dto.request.ShiftRequest;
import com.group19.OrderManagementSystem_backend.dto.response.ShiftResponse;
import com.group19.OrderManagementSystem_backend.entity.Shift;
import com.group19.OrderManagementSystem_backend.entity.Table;
import com.group19.OrderManagementSystem_backend.exception.AppException;
import com.group19.OrderManagementSystem_backend.exception.ErrorCode;
import com.group19.OrderManagementSystem_backend.mapper.ShiftMapper;
import com.group19.OrderManagementSystem_backend.repository.ShiftRepository;
import com.group19.OrderManagementSystem_backend.repository.TableRepository;
import com.group19.OrderManagementSystem_backend.utils.TableStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private ShiftMapper shiftMapper;
    @Autowired
    private TableRepository tableRepository;

    public List<ShiftResponse> findAllShiftByDate(ShiftRequest shiftRequest) {
        List<Shift> shifts = shiftRepository.findAllByDate(LocalDate.now());
        return shiftMapper.toListShiftResponse(shifts);
    }

    public List<ShiftResponse> findAllShifts() {
        List<Shift> shifts = shiftRepository.findAll();
        return shiftMapper.toListShiftResponse(shifts);
    }

    public ShiftResponse createShift() {
        List<Shift> shifts = shiftRepository.findByDateAndIsEnabledTrue(LocalDate.now());
        if (shifts.size() > 0)
            throw new AppException(ErrorCode.SHIFT_ALREADY_ACTIVE);
        Shift shift = Shift.builder()
                .date(LocalDate.now())
                .startTime(LocalTime.now())
                .endTime(null)
                .isEnabled(true)
                .build();
        shiftRepository.save(shift);
        return shiftMapper.toShiftResponse(shift);
    }

    public List<ShiftResponse> findShiftByDate(LocalDate date) {
        List<Shift> shifts = shiftRepository.findAllByDate(date);
        return shiftMapper.toListShiftResponse(shifts);
    }

    public ShiftResponse closeShift(String shiftId) {
        Shift shift = shiftRepository.findByShiftId(shiftId)
                .orElseThrow(() -> new AppException(ErrorCode.SHIFT_NOT_EXITED));
        shift.setEnabled(false);
        shift.setEndTime(LocalTime.now());
        List<Table> tables = tableRepository.findAll();
        for (Table table : tables) {
            if (table.getStatus().equals(TableStatus.UNAVAILABLE.name())) {
                table.setStatus(TableStatus.AVAILABLE.name());
            }
        }
        tableRepository.saveAll(tables);
        shiftRepository.save(shift);
        return shiftMapper.toShiftResponse(shift);
    }

    public ShiftResponse findShiftByIsEnabled() {
        Shift shift = shiftRepository.findByIsEnabledTrue()
                .orElseThrow(() -> new AppException(ErrorCode.NO_ACTIVE_SHIFT));
        return shiftMapper.toShiftResponse(shift);
    }
}
