package com.project.springbootwebstore.service;

import com.project.springbootwebstore.dto.CartItem;
import com.project.springbootwebstore.dto.FavoriteItem;
import com.project.springbootwebstore.dto.product.ProductSubcategoryResponse;
import com.project.springbootwebstore.dto.product.ProductFullInfoResponse;
import com.project.springbootwebstore.dto.product.ProductShortInfoResponse;
import com.project.springbootwebstore.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ProductService {


    ProductFullInfoResponse getProductById(Long id);

    ProductShortInfoResponse getSingleCartProduct(CartItem item);


    ProductShortInfoResponse getSingleFavoriteProduct(FavoriteItem item);


    Page<Product> filter(Map<String, String[]> filteringAttributes, String subcategory);

    Map<Long, ProductSubcategoryResponse> countProductOccurrences(String query);

    Page<ProductShortInfoResponse> getProductPage(Map<String, String[]> parameters, String subcategoryName);

    Page<ProductShortInfoResponse> search(String query, Pageable pageable);

    Double getProductCartPrice(CartItem cartItem);

    List<Product> getAllProductsById(List<Long> productIds);
}
