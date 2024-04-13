package com.project.springbootwebstore.dto.product;


public record ProductSubcategoryResponse(
        Long id,
        String name,
        String category,
        String imagePath
) {
}
