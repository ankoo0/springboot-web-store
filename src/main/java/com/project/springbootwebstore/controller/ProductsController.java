package com.project.springbootwebstore.controller;


import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.service.ProductCategoryService;
import com.project.springbootwebstore.model.service.ProductService;
import com.project.springbootwebstore.model.service.ProductSubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/main")
public class ProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService categoryService;

    @Autowired
    ProductSubcategoryService subcategoryService;

    @GetMapping
    public ModelAndView mainView() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        categoryService.getAllCategories().forEach(System.out::println);
        return modelAndView;
    }

    @GetMapping("/{category}/{subcategory}")
    public ModelAndView subcategoryView(@RequestParam(name = "page") int page, @PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory) {
        ProductSubcategory subcategory1 = subcategoryService.getSubcategoryByName(subcategory);
        System.out.println(subcategory1);
        Page<Product> productPages = productService.getProductPages(page, 5, subcategory1.getId());
        List<Product> products = productPages.getContent();

        System.out.println("==================================");
//        products.forEach(System.out::println);
        productPages.stream().forEach(System.out::println);
        System.out.println(productPages.getTotalPages());
        System.out.println(productPages.getTotalElements());
        System.out.println("==================================");
        ModelAndView mov = new ModelAndView("products");
        mov.addObject("currentPage", page);
        mov.addObject("products", products);
        mov.addObject("totalPages", productPages.getTotalPages());
        mov.addObject("totalProducts", productPages.getTotalElements());

        return mov;
    }


    @GetMapping("/{category}/{subcategory}/{productId}")
    public ModelAndView productView(@PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory, @PathVariable(name = "productId") Long productId) {
        Product product = productService.getProductById(productId);
        ModelAndView mov = new ModelAndView("product-view");
        mov.addObject("product", product);
        return mov;
    }

}
