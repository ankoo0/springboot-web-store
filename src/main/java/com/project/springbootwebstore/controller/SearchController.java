package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.product.ProductSubcategoryResponse;
import com.project.springbootwebstore.dto.product.ProductShortInfoResponse;
import com.project.springbootwebstore.dto.Query;
import com.project.springbootwebstore.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
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

    private final ProductServiceImpl productService;

    @PostMapping("/count")
    public Map<Long, ProductSubcategoryResponse> getOccurrences(@RequestBody(required = false) Query query){
        return productService.countProductOccurrences(query.getQuery());
    }

    @PostMapping()
    public List<ProductShortInfoResponse> search(@RequestBody(required = false) Query query)  {

        if (query!=null){
            Pageable searchPage=PageRequest.ofSize(5);

            return productService.search(query.getQuery(),searchPage).toList();
        } else {
            return Collections.emptyList();
        }

    }
}
