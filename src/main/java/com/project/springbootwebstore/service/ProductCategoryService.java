package com.project.springbootwebstore.service;

import com.project.springbootwebstore.dto.ProductCategoryDto;
import com.project.springbootwebstore.entity.product.ProductSubcategory;
import com.project.springbootwebstore.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {


    private final ProductCategoryRepository categoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<ProductCategoryDto> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(c-> new ProductCategoryDto.Builder()
                        .setId(c.getId())
                        .setCategoryName(c.getCategoryName())
                        .setCategoryImage(c.getCategoryImage())
                        .setSubcategories(c.getSubcategories().stream().map(ProductSubcategory::getSubcategoryName).collect(Collectors.toList()))
                        .build())
                .toList();
    }
}
