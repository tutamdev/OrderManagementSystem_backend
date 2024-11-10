package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String discountCode;
    private String discountType;
    private String discountValue;
    private String status;
    @OneToOne(mappedBy = "discount", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Order  order;
}
