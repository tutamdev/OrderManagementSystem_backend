package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_shift")
@Builder
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String shiftId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isEnabled;

    @OneToMany(
            mappedBy = "shift",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> orders;
}
