package com.project.springbootwebstore.model.dto.mappers;

import com.project.springbootwebstore.model.dto.ProductToProductViewDto;
import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductAttribute;
import com.project.springbootwebstore.model.entity.product.ProductImagePath;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductToProductViewDtoMapper implements Function<Product, ProductToProductViewDto> {
    @Override
    public ProductToProductViewDto apply(Product product) {
        return new ProductToProductViewDto(
                product.getId(),
                product.getName(),
                product.getFullDescription(),
                product.getMainThumbnailPath(),
                product.getProductImagesPaths()
                        .stream()
                        .map(ProductImagePath::getImagePath)
                        .toList(),
                product.getPrice(),
                product.getRating(),
                product.getQuantity(),
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
