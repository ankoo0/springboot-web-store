package com.project.springbootwebstore.dto.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

// poor record getter support in thymeleaf/spel
@RequiredArgsConstructor
@Getter
public final class ProductFullInfoResponse {
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
    private final Map<String, String> attributes;


}