package com.project.springbootwebstore.model.entity.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class ProductSubcategory {

    @Id
    private Long id;
    private String subcategoryName;
    @ManyToOne
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "productSubcategory")
    private List<ProductAttribute> productAttributes;

}
