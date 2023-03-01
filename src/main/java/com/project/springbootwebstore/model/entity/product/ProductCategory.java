package com.project.springbootwebstore.model.entity.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProductCategory {

    @Id
    private Long id;
    private String categoryName;
    private String categoryImage;
    @OneToMany(mappedBy = "productCategory")
    private List<ProductSubcategory> subcategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public ProductCategory(Long id, String categoryName, List<ProductSubcategory> subcategories) {
        this.id = id;
        this.categoryName = categoryName;
        this.subcategories = subcategories;
    }

    public ProductCategory() {

    }
}
