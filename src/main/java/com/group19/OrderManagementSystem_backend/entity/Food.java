package com.group19.OrderManagementSystem_backend.entity;

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

    @Column(name = "food_name")
    private String foodName;

    @Column(name = "food_price")
    private String foodPrice;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "availability")
    private boolean availability;

    @OneToMany(
            mappedBy = "food",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderDetail> orderDetails;
}
