package com.project.springbootwebstore.entity.order;

import com.project.springbootwebstore.entity.users.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "_order")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User orderedUser;
    private LocalDateTime orderTime;

    @Embedded
    private DeliveryDetails deliveryDetails;

    @Embedded
    private UserOrderInfo userOrderInfo;

//    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "character varying")
    private String orderedProducts;

    public Order(User orderedUser, LocalDateTime orderTime, DeliveryDetails deliveryDetails, UserOrderInfo userOrderInfo, String orderedProducts) {
        this.orderedUser = orderedUser;
        this.orderTime = orderTime;
        this.deliveryDetails = deliveryDetails;
        this.userOrderInfo = userOrderInfo;
        this.orderedProducts = orderedProducts;
    }
}
