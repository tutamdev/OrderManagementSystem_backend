package com.group19.OrderManagementSystem_backend.entity;

import com.group19.OrderManagementSystem_backend.utils.ERole;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalTime;

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
    private String id;
	
	@Column(name="note")
	private String note;
	
	@Column(name ="total_price")
	private double total_price;
	
	@Column(name ="created_at")
	private LocalTime created_at;
	
	@Column(name ="end_at")
	private LocalTime ended_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Employee employee;
}
