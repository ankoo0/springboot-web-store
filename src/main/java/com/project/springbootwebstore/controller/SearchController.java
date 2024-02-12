package com.project.springbootwebstore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.springbootwebstore.dto.ProductSubcategoryDto;
import com.project.springbootwebstore.dto.ProductToListViewDto;
import com.project.springbootwebstore.dto.Query;
import com.project.springbootwebstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final ProductService productService;

    @PostMapping("/count")
    public Map<Long, ProductSubcategoryDto> getOccurrences(@RequestBody(required = false) Query query){
        Map<Long,ProductSubcategoryDto> map = productService.countProductOccurrences(query.getQuery());

        return map;
    }


    @PostMapping()
    public List<ProductToListViewDto> search(@RequestBody(required = false) Query query)  {

        if (query!=null){
            Pageable searchPage=PageRequest.ofSize(5);
//            System.out.println(query.getQuery());
//            System.out.println(productService.search(query.getQuery(),searchPage));
            return productService.search(query.getQuery(),searchPage).toList();
        } else {
            return Collections.emptyList();
        }

    }
}
