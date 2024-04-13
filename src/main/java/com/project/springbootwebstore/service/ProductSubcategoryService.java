package com.project.springbootwebstore.service;

import com.project.springbootwebstore.entity.product.ProductSubcategory;
import com.project.springbootwebstore.repository.ProductSubcategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductSubcategoryService {

    private final ProductSubcategoryRepository subcategoryRepository;

    public List<ProductSubcategory> getAllSubcategories(){
        return subcategoryRepository.findAll();
    }

    public ProductSubcategory getSubcategoryByName(String subcategoryName){
        return subcategoryRepository.findByNameContainingIgnoreCase(subcategoryName);

    }

}
