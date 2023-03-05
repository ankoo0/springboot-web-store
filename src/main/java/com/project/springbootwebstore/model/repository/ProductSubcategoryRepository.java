package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSubcategoryRepository extends JpaRepository<ProductSubcategory, Long> {

    ProductSubcategory findBySubcategoryNameContainingIgnoreCase(String subcategoryName);
}