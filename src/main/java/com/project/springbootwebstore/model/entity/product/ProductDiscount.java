package com.project.springbootwebstore.model.entity.product;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class ProductDiscount {

    @Id
    private Long id;
    private Integer discountPercent;
    @OneToMany(mappedBy = "discount")
    private List<Product> products;
////    @OneToOne
//    private Product product;

}
