package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

//    public List<Product> getBySubcategoryId(Long subcategoryId){
//       return productRepository.findAllBySubcategoryId(subcategoryId);
//    }

//    public List<Product> getBySubcategory(String subcategoryName){
//        return productRepository.findAllBySubcategory(subcategoryName);
//    }

    public Page<Product> getProductPages(int pageNumber, int pageSize,Long subcategoryId){
        // -1 because pagination counts from 0
        Pageable productPages = PageRequest.of(pageNumber-1,pageSize);
        return productRepository.findAllBySubcategoryId(productPages,subcategoryId);
    }
    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }
}
