package com.project.springbootwebstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.springbootwebstore.model.dto.ProductDto;
import com.project.springbootwebstore.model.dto.ProductSubcategoryDto;
import com.project.springbootwebstore.model.dto.ProductToListViewDto;
import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    ProductService productService;


    @PostMapping("/count")
    public Map<Long, ProductSubcategoryDto> getOccurrences(@RequestBody(required = false) Query query){
        Map<Long,ProductSubcategoryDto> map = productService.countProductOcurrencies(query.getQuery());

        return map;
    };


    @PostMapping()
    public List<ProductToListViewDto> search(@RequestBody(required = false) Query query) throws JsonProcessingException {
//       Map<ProductSubcategory, Long> map = productService.countProductOcurrencies(query.getQuery());
//        System.out.println(map);
//        String json = new ObjectMapper().writeValueAsString(map);
//        System.out.println(map);
//        System.out.println("==============");

//        if (query!=null){
//            System.out.println(query);
//            List<Product> products = new ArrayList<>();
//            try {
//                products =  productService.search(query);
//
////          products =  productService.searchProducts(query,List.of("name", "shortDescription","fullDescription"),20);
//            }
//            catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            System.out.println(products);
//            return productService.search(query);
//        }

        if (query!=null){
            Pageable searchPage=PageRequest.ofSize(5);
            System.out.println(query.getQuery());
            System.out.println(productService.search(query.getQuery(),searchPage));
            return productService.search(query.getQuery(),searchPage).toList();
        }

        return Collections.emptyList();
    }
}
