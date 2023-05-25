package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.product.ProductAttribute;
import com.project.springbootwebstore.model.service.ProductAttributeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<ProductAttribute,Long> {

    List<ProductAttribute> findAllByProductSubcategorySubcategoryName(String subcategoryName);
}
