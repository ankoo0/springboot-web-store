package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
   Page<Product> findAllBySubcategoryId(Pageable productPages,Long subcategoryId);
//   List<Product> findAllBySubcategoryId(Long subcategoryId);

//   @Query(value = "SELECT * FROM product WHERE category_id = (select * from )", nativeQuery = true)
//   List<Product> findAllBySubcategory(ProductSubcategory subcategory);
}