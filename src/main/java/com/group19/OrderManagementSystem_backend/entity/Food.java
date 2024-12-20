package com.group19.OrderManagementSystem_backend.entity;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    private BigDecimal foodPrice;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
