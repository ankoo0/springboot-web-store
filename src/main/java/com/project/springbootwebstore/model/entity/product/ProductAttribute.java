package com.project.springbootwebstore.model.entity.product;

import javax.persistence.*;

@Entity
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String attributeName;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductSubcategory productSubcategory;


}
