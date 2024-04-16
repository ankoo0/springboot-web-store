package com.project.springbootwebstore.repository;

import com.project.springbootwebstore.entity.product.ProductSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSubcategoryRepository extends JpaRepository<ProductSubcategory, Long> {

    ProductSubcategory findByNameContainingIgnoreCase(String subcategoryName);

}