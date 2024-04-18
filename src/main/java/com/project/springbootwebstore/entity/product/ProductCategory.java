package com.project.springbootwebstore.entity.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "imagePath")
    private String imagePath;

    @OneToMany(mappedBy = "category")
    private List<ProductSubcategory> subcategories = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public ProductCategory(Long id, String name, List<ProductSubcategory> subcategories) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", categoryName='" + name + '\'' +
                ", categoryImage='" + imagePath + '\'' +
                ", subcategories=" + subcategories.stream().map(ProductSubcategory::getName).toList() +
                ", products=" + products.stream().map(Product::getName).toList() +
                '}';
    }
}
