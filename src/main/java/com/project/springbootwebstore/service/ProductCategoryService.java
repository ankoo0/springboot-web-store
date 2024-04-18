package com.project.springbootwebstore.service;

import com.project.springbootwebstore.dto.product.ProductCategoryResponse;
import com.project.springbootwebstore.dto.product.ProductMapper;
import com.project.springbootwebstore.entity.product.ProductSubcategory;
import com.project.springbootwebstore.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository categoryRepository;

    public List<ProductCategoryResponse> getAllCategories(){
        return categoryRepository.findAll()
                .stream()
                .map(ProductMapper.PRODUCT_MAPPER::toCategoryResponse)
                .toList();
    }
}
