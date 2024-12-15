package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_discount")
public class Discount {
    @Id
    private String discountCode;
    private String discountType;
    private double discountValue;
    private boolean status;

}
