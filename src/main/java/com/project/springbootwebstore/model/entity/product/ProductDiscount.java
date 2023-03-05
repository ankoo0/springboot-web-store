package com.project.springbootwebstore.model.entity.product;


import javax.persistence.*;
import java.util.List;

@Entity
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer discountPercent;
    @OneToMany(mappedBy = "discount")
    private List<Product> products;
////    @OneToOne
//    private Product product;

}
