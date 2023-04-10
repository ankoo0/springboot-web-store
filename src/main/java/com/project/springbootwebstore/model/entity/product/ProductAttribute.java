package com.project.springbootwebstore.model.entity.product;

import javax.persistence.*;

@Entity
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String attributeName;
    private String attributeValue;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ProductSubcategory productSubcategory;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductSubcategory getProductSubcategory() {
        return productSubcategory;
    }

    public void setProductSubcategory(ProductSubcategory productSubcategory) {
        this.productSubcategory = productSubcategory;
    }
}
