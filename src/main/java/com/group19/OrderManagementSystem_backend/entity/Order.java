package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_order")

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    @Column(name = "note")
    private String note;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "end_at")
    private LocalDateTime endedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tableId")
    private com.group19.OrderManagementSystem_backend.entity.Table table;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shiftId")
    private Shift shift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_code", referencedColumnName = "discountCode")
    private Discount discount;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails;
}
