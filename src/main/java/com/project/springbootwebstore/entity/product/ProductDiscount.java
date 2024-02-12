package com.project.springbootwebstore.entity.product;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class ProductDiscount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long discountPercent;
    @OneToMany(mappedBy = "discount")
    private List<Product> products;
////    @OneToOne
//    private Product product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Long discountPercent) {
        this.discountPercent = discountPercent;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
