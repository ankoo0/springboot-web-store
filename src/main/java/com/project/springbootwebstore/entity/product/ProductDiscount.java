package com.project.springbootwebstore.entity.product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long discountPercent;
    @OneToMany(mappedBy = "discount")
    private List<Product> products;
////    @OneToOne
//    private Product product;


}
