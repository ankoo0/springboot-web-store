package com.project.springbootwebstore.model.dto;

import java.util.Map;

public class ProductToListViewDto {
    private final Long id;
    private final String name;
    private final String shortDescription;
    private final String mainThumbnailPath;
    //    private final List<String> productImagesPaths;
    private final Double price;
    private final Long quantity;
    private final Long discount;
    private final Double rating;
    private final String category;
    private final String subcategory;
    private final Map<String, String> productAttributes;

    public ProductToListViewDto(Long id,
                                String name,
                                String shortDescription,
                                String mainThumbnailPath,
                                Double price,
                                Long quantity,
                                Double rating,
                                String category,
                                String subcategory,
                                Long discount,
                                Map<String, String> productAttributes) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.mainThumbnailPath = mainThumbnailPath;
        this.rating = rating;
        this.quantity=quantity;
        this.category = category;
        this.subcategory = subcategory;
        this.productAttributes = productAttributes;
        this.discount=discount;
        this.price= discount > 0 ? Double.valueOf(price - ((price / 100) * discount)) : price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getMainThumbnailPath() {
        return mainThumbnailPath;
    }

    public Double getPrice() {
        return price;
    }

    public Long getDiscount() {
        return discount;
    }

    public Double getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public Map<String, String> getProductAttributes() {
        return productAttributes;
    }

    public Long getQuantity() {
        return quantity;
    }
}
