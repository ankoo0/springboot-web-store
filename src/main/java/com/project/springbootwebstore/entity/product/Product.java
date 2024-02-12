package com.project.springbootwebstore.entity.product;

import com.project.springbootwebstore.entity.order.Order;
import com.project.springbootwebstore.entity.users.Review;
import org.hibernate.Hibernate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name",nullable = false)
    private String name;
    @Column(nullable = false)
    private String fullDescription;
    @Column(nullable = false)
    private String mainThumbnailPath;
    @OneToMany(mappedBy = "product")
    private List<ProductImagePath> productImagesPaths;
    @OneToMany(mappedBy = "product")
    private List<ProductAttribute> productAttributes;
    @Column(nullable = false)
    private LocalDateTime creationTime;
    @Column(scale =2)
    private Double price;
    @Column(nullable = false)
    private Long quantity;
    @Column(columnDefinition = "real default 0.0")
    private Double rating;
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;
    @JoinColumn(name = "category_id")
    @ManyToOne
    private ProductCategory category;
    @JoinColumn(name = "subcategory_id")
    @ManyToOne
    private ProductSubcategory subcategory;
    @ManyToOne
    private ProductDiscount discount;
    @ManyToMany
    private List<Order> ordersWithProduct;

    public Product(Long id) {
        this.id = id;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getMainThumbnailPath() {
        return mainThumbnailPath;
    }

    public void setMainThumbnailPath(String mainThumbnailPath) {
        this.mainThumbnailPath = mainThumbnailPath;
    }

    public List<ProductImagePath> getProductImagesPaths() {
        return productImagesPaths;
    }

    public void setProductImagesPaths(List<ProductImagePath> productImagesPaths) {

        this.productImagesPaths = productImagesPaths;
    }

    public List<ProductAttribute> getProductAttributes() {
        return productAttributes;
    }

    public void setProductAttributes(List<ProductAttribute> productAttributes) {
        this.productAttributes = productAttributes;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public ProductSubcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(ProductSubcategory subcategory) {
        this.subcategory = subcategory;
    }

    public ProductDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(ProductDiscount discount) {
        this.discount = discount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "fullDescription = " + fullDescription + ", " +
                "mainThumbnailPath = " + mainThumbnailPath + ", " +
                "creationTime = " + creationTime + ", " +
                "price = " + price + ", " +
                "category = " + category + ", " +
                "discount = " + discount + ")";
    }



    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
