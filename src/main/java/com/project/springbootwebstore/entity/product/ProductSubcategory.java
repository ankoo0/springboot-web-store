package com.project.springbootwebstore.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subcategoryName;
    @ManyToOne

    @JsonIgnore
    private ProductCategory productCategory;


    @OneToMany(mappedBy = "productSubcategory")
    private List<ProductAttribute> productAttributes;

    @OneToMany(mappedBy = "subcategory")
    private List<Product> products = new ArrayList<>();

    private String subcategoryImage;




    public ProductSubcategory() {

    }

    public ProductSubcategory(Long id, String subcategoryName, ProductCategory productCategory, List<ProductAttribute> productAttributes, List<Product> products) {
        this.id = id;
        this.subcategoryName = subcategoryName;
        this.productCategory = productCategory;
        this.productAttributes = productAttributes;
        this.products = products;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubcategoryName() {
        return subcategoryName;
    }

    public void setSubcategoryName(String subcategoryName) {
        this.subcategoryName = subcategoryName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


    public String getSubcategoryImage() {
        return subcategoryImage;
    }

    public void setSubcategoryImage(String subcategoryImage) {
        this.subcategoryImage = subcategoryImage;
    }

    @Override
    public String toString() {
        return "ProductSubcategory{" +
                "id=" + id +
                ", subcategoryName='" + subcategoryName + '\'' +
                ", productCategory=" + productCategory +
                ", productAttributes=" + productAttributes +
                '}';
    }
}
