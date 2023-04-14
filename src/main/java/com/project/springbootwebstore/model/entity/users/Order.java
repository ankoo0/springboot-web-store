package com.project.springbootwebstore.model.entity.users;

import com.project.springbootwebstore.model.entity.product.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User orderedUser;
    private LocalDateTime orderTime;
    @ManyToMany
    private List<Product> orderedProducts;
}
