package com.project.springbootwebstore.model.entity.product;

import javax.persistence.*;

@Entity
public class ProductImagePath {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imagePath;
    @ManyToOne
    private Product product;
}
