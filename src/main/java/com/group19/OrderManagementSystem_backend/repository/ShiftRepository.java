package com.group19.OrderManagementSystem_backend.repository;

import com.group19.OrderManagementSystem_backend.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, String> {
    List<Shift> findAllByDate(LocalDate date);

    List<Shift> findByDateAndIsEnabledTrue(LocalDate date);

    Optional<Shift> findByShiftId(String shiftId);

    Optional<Shift> findByIsEnabledTrue();
}
