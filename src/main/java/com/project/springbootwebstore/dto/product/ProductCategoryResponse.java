package com.project.springbootwebstore.dto.product;

import java.util.List;

public record ProductCategoryResponse(
        Long id,
        String name,
        String imagePath,
        List<String> subcategories
) {
}
