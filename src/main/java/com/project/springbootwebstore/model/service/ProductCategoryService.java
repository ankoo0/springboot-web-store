package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.dto.ProductCategoryDto;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository categoryRepository;

    public List<ProductCategoryDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(c-> new ProductCategoryDto.Builder()
                        .setId(c.getId())
                        .setCategoryName(c.getCategoryName())
                        .setCategoryImage(c.getCategoryImage())
                        .setSubcategories(c.getSubcategories().stream().map(ProductSubcategory::getSubcategoryName).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }
}
