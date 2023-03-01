package com.project.springbootwebstore.controller;


import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("{category}")
    public String getAllProducts(@PathVariable(name = "category") String category, Model model) {
        model.addAttribute("products",productService.getByCategory(new ProductCategory(1L,category, Collections.emptyList())));
        return "products";
    }

    @PostMapping
    public String getProduct(){
        return null;
    }


}
