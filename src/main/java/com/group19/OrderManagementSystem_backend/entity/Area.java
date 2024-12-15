package com.group19.OrderManagementSystem_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "tbl_area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String areaId;
    private String areaName;
    private String description;

    @OneToMany(
            mappedBy = "area",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Table> tables;
}
