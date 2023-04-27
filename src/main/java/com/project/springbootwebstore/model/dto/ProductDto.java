package com.project.springbootwebstore.model.dto;

import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductImagePath;

import java.util.List;

public class ProductDto {
    private final Long id;
    private final String name;
    private final String shortDescription;
    private final String fullDescription;
    private String mainThumbnailPath;
    private List<String> productImagesPaths;
    private Double price;
    private Long quantity;
    private Long rating;
    private ProductCategory category;

}
