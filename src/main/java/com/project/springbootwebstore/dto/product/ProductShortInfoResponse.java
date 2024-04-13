package com.project.springbootwebstore.dto.product;

import java.util.Map;

public record ProductShortInfoResponse(
    Long id,
    String name,
    String mainThumbnailPath,
    Double price,
    Long quantity,
    Long discount,
    Double rating,
    String category,
    String subcategory,
    Map<String, String> attributes) {
}
