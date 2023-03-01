package com.project.springbootwebstore.model.entity.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ProductImagePath {
    @Id
    private Long id;
    private String imagePath;
    @ManyToOne
    private Product product;
}
