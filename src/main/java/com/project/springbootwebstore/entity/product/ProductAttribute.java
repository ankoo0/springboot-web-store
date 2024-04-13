package com.project.springbootwebstore.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_attribute")
@Getter
@Setter
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductSubcategory productSubcategory;

    @ManyToOne
    @JoinColumn
    private ProductAttributeDescription description;


}
