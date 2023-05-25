package com.project.springbootwebstore.model.dto;

import java.util.Map;

public  final class ProductToProductViewDto {

    private final Long id;
    private final String name;
    private final String fullDescription;
    private final String shortDescription;
    private final String mainThumbnailPath;
    private final Double price;
    private final Long quantity;
    private final Long rating;
    private final Long discount;
    private final Map<String, String> productAttributes;

    public ProductToProductViewDto(Long id, String name, String fullDescription, String shortDescription, String mainThumbnailPath, Double price, Long quantity, Long rating, Long discount, Map<String, String> productAttributes) {
        this.id = id;
        this.name = name;
        this.fullDescription = fullDescription;
        this.shortDescription = shortDescription;
        this.mainThumbnailPath = mainThumbnailPath;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public String getMainThumbnailPath() {
        return mainThumbnailPath;
    }

    public Double getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long getRating() {
        return rating;
    }

    public Long getDiscount() {
        return discount;
    }

    public Map<String, String> getProductAttributes() {
        return productAttributes;
    }
}
