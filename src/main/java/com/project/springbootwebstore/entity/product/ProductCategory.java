package com.project.springbootwebstore.entity.product;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public List<ProductSubcategory> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<ProductSubcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryImage='" + categoryImage + '\'' +
                ", subcategories=" + subcategories.stream().map(ProductSubcategory::getSubcategoryName).toList() +
                ", products=" + products.stream().map(Product::getName).toList() +
                '}';
    }
}
