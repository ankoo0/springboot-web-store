package com.project.springbootwebstore.service;

import com.project.springbootwebstore.entity.product.ProductSubcategory;
import com.project.springbootwebstore.repository.ProductSubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSubcategoryService {


    ProductSubcategoryRepository subcategoryRepository;

    @Autowired
    public ProductSubcategoryService(ProductSubcategoryRepository subcategoryRepository) {
        this.subcategoryRepository = subcategoryRepository;
    }

    public List<ProductSubcategory> getAllSubcategories(){
        return subcategoryRepository.findAll();
    }

    public ProductSubcategory getSubcategoryByName(String subcategoryName){
        return subcategoryRepository.findBySubcategoryNameContainingIgnoreCase(subcategoryName);

    }

}
