package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.repository.ProductSubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSubcategoryService {

    @Autowired
    ProductSubcategoryRepository subcategoryRepository;

    public List<ProductSubcategory> getAllSubcategories(){
        return subcategoryRepository.findAll();
    }

    public ProductSubcategory getSubcategoryByName(String subcategoryName){
        return subcategoryRepository.findBySubcategoryNameContainingIgnoreCase(subcategoryName);

    }

}
