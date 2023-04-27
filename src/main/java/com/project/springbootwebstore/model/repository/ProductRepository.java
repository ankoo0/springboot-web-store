package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends SearchRepository<Product,Long> {

   @Query(value = "select count(id) from product where document @@ plainto_tsquery(?1) and subcategory_id=?2", nativeQuery = true)
   Long countAllBySubcategory(@Param("query") String query,@Param("subcategoryId")Long subcategoryId);

   @Query(value = "select * from product where document @@ plainto_tsquery(?1)", nativeQuery = true)
   Page<Product> getAllByName(@Param("query") String query,Pageable pageable);
   Page<Product> findAllBySubcategoryId(Pageable productPages,Long subcategoryId);
//   List<Product> findAllBySubcategoryId(Long subcategoryId);

//   @Query(value = "SELECT * FROM product WHERE category_id = (select * from )", nativeQuery = true)
//   List<Product> findAllBySubcategory(ProductSubcategory subcategory);
}