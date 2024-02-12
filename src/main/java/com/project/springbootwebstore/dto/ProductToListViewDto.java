package com.project.springbootwebstore.dto;

import java.util.Map;

public record ProductToListViewDto(
    Long id,
    String name,
    String mainThumbnailPath,
    Double price,
    Long quantity,
    Long discount,
    Double rating,
    String category,
    String subcategory,
    Map<String, String> productAttributes) {
}


//        this.price= discount > 0 ? Double.valueOf(price - ((price / 100) * discount)) : price;

