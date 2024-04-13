package com.project.springbootwebstore.repository;

import com.project.springbootwebstore.entity.product.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<ProductAttribute,Long> {

    List<ProductAttribute> findDistinctByProductSubcategoryName(String subcategoryName);
}
