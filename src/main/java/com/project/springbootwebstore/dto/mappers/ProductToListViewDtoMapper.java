package com.project.springbootwebstore.dto.mappers;

import com.project.springbootwebstore.dto.ProductToListViewDto;
import com.project.springbootwebstore.entity.product.Product;
import com.project.springbootwebstore.entity.product.ProductAttribute;
import org.springframework.stereotype.Component;


import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public final class ProductToListViewDtoMapper implements Function<Product, ProductToListViewDto> {

    @Override
    public ProductToListViewDto apply(Product product) {
        return new ProductToListViewDto(
                product.getId(),
                product.getName(),
                product.getShortDescription(),
                product.getMainThumbnailPath(),
                product.getPrice(),
                product.getQuantity(),
                product.getRating(),
                product.getCategory().getCategoryName(),
                product.getSubcategory().getSubcategoryName(),
                product.getDiscount().getDiscountPercent(),
                product.getProductAttributes()
                        .stream()
                        .collect(Collectors.toMap(ProductAttribute::getAttributeName,
                                ProductAttribute::getAttributeValue))

        );
    }
}
