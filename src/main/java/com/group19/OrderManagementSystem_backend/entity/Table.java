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
@jakarta.persistence.Table(name = "tbl_table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tableId;
    private String tableName;
    private String description;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;
}
