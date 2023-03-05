package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository categoryRepository;

    public List<ProductCategory> getAllCategories(){
        return categoryRepository.findAll();
    }
}
