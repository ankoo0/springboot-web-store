package com.project.springbootwebstore.entity.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class ProductSubcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnore
    private ProductCategory category;

    @OneToMany(mappedBy = "productSubcategory")
    private List<ProductAttribute> attributes;

    @OneToMany(mappedBy = "subcategory")
    private List<Product> products = new ArrayList<>();

    private String imagePath;


}
