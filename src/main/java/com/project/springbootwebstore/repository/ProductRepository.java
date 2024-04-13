package com.project.springbootwebstore.repository;

import com.project.springbootwebstore.entity.product.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    Page<Product> findAll(Specification<Product> spec,
                          Pageable pageable);

    @Query(value = "select count(id) from product where document @@ plainto_tsquery(?1) and subcategory_id=?2", nativeQuery = true)
    Long countAllBySubcategory(@Param("query") String query, @Param("subcategoryId") Long subcategoryId);

    @Query(value = "select * from product where document @@ plainto_tsquery(?1)", nativeQuery = true)
    Page<Product> getAllByName(@Param("query") String query, Pageable pageable);

    Page<Product> findAllBySubcategoryId(Pageable productPages, Long subcategoryId);

    @Query(value = "select * from product where document @@ plainto_tsquery(?1) and subcategory_id=?2", nativeQuery = true)
    Page<Product> findAllBySubcategoryIdAndQuery(Pageable productPages, @Param("query") String query, @Param("subcategoryId") Long subcategoryId);

}