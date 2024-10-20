package com.group19.OrderManagementSystem_backend.entity;

import com.group19.OrderManagementSystem_backend.utils.ERole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

import javax.management.relation.Role;

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
    private String order_id;
	
	@Column(name="note")
	private String note;
	
	@Column(name ="total_price")
	private double total_price;
	
	@Column(name ="created_at")
	private LocalTime created_at;
	
	@Column(name ="end_at")
	private LocalTime ended_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="tableId")
	private com.group19.OrderManagementSystem_backend.entity.Table table;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shiftId")
    private Shift shift;

	@OneToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name="discountCode")
	private Discount discount;
	
	@OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order_detail> order_details;
}
