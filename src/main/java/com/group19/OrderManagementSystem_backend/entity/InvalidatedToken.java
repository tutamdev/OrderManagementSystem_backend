package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_invalidated_token")
public class InvalidatedToken {
    @Id
    private String id;
    private Date expiryTime;
}
