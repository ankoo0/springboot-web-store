package com.project.springbootwebstore.dto.product;

import com.project.springbootwebstore.entity.product.*;

import java.util.Collections;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "discount", source = "discount.discountPercent")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "subcategory", source = "subcategory.name")
    @Mapping(target = "attributes", source = "attributes", qualifiedByName = "productAttributesToMap" )
    ProductShortInfoResponse toShortInfoResponse(Product product);

    @Mapping(target = "fullDescription", source = "fullDescription")
    @Mapping(target = "mainThumbnailPath", source = "mainThumbnailPath")
    @Mapping(target = "productImagesPaths", source = "productImagesPaths", qualifiedByName = "mapProductImagesPaths")
    @Mapping(target = "category", source = "category.name")
    @Mapping(target = "subcategory", source = "subcategory.name")
    @Mapping(target = "discount", source = "discount.discountPercent")
    @Mapping(target = "attributes", source = "attributes", qualifiedByName = "productAttributesToMap" )
    ProductFullInfoResponse toFullInfoResponse(Product product);

    @Mapping(target = "subcategories", source = "subcategories", qualifiedByName = "mapSubcategories")
    ProductCategoryResponse toCategoryResponse(ProductCategory category);

    @Mapping(target = "category", source = "category.name")
    ProductSubcategoryResponse toSubcategoryResponse(ProductSubcategory subcategory);

    @Named("mapProductImagesPaths")
    default List<String> mapProductImagesPaths(List<ProductImagePath> productImagesPaths) {
        return productImagesPaths.stream()
                .map(ProductImagePath::getImagePath)
                .toList();
    }

    @Named("mapSubcategories")
    default List<String> mapSubcategories(List<ProductSubcategory> subcategories){
        return subcategories.stream().map(ProductSubcategory::getName).toList();
    }

    @Named("productAttributesToMap")
    default Map<String, String> productAttributesToMap(List<ProductAttribute> productAttributes) {
        if (productAttributes == null) {
            return Collections.emptyMap();
        }
        return productAttributes.stream()
                .collect(Collectors.toMap(ProductAttribute::getName, ProductAttribute::getValue));
    }
}
