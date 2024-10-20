package com.group19.OrderManagementSystem_backend.entity;

import com.group19.OrderManagementSystem_backend.dto.response.CategoryResponse;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String foodId;

    private String foodName;
    private long foodPrice;
    private String description;
    private String imgUrl;
    private boolean availability;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
