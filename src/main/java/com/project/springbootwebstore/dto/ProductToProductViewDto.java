package com.project.springbootwebstore.dto;

import java.util.List;
import java.util.Map;

public final class ProductToProductViewDto {

    private final Long id;
    private final String name;
    private final String fullDescription;
    private final String mainThumbnailPath;
    private final List<String> productImagesPaths;
    private final Double price;
    private final Double rating;
    private final Long quantity;
    private final String category;
    private final String subcategory;
    private final Long discount;
    private final Map<String, String> productAttributes;

    public ProductToProductViewDto(Long id,
                                   String name,
                                   String fullDescription,
                                   String mainThumbnailPath,
                                   List<String> productImagesPaths,
                                   Double price,
                                   Double rating,
                                   Long quantity,
                                   String category,
                                   String subcategory,
                                   Long discount,
                                   Map<String, String> productAttributes) {
        this.id = id;
        this.name = name;
        this.fullDescription = fullDescription;
        this.mainThumbnailPath = mainThumbnailPath;
        this.productImagesPaths=productImagesPaths;
        this.rating = rating;
        this.quantity=quantity;
        this.category = category;
        this.subcategory = subcategory;
        this.price= discount > 0 ? Double.valueOf(price - ((price / 100) * discount)) : price;
        this.discount = discount;
        this.productAttributes = productAttributes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public String getMainThumbnailPath() {
        return mainThumbnailPath;
    }

    public List<String> getProductImagesPaths() {
        return productImagesPaths;
    }

    public Double getPrice() {
        return price;
    }

    public Double getRating() {
        return rating;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public Long getDiscount() {
        return discount;
    }

    public Map<String, String> getProductAttributes() {
        return productAttributes;
    }
}
