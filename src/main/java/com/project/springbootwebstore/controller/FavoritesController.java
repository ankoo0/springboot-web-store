package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.FavoriteItem;
import com.project.springbootwebstore.dto.ProductToListViewDto;
import com.project.springbootwebstore.service.ProductCategoryService;
import com.project.springbootwebstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {


    private final ProductService productService;
    private final ProductCategoryService categoryService;

    @Autowired
    public FavoritesController(ProductService productService, ProductCategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }




    @PostMapping
    public @ResponseBody List<ProductToListViewDto> favoriteItems(@RequestBody List<FavoriteItem> items) {

        List<ProductToListViewDto> responseProducts = new ArrayList<>();
        for (FavoriteItem item : items) {
            ProductToListViewDto product = productService.getSingleFavoriteProduct(item);
            responseProducts.add(product);
        }
        return responseProducts;
    }

    @GetMapping
    public ModelAndView favoritesView() {
        ModelAndView modelAndView = new ModelAndView("favorites");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }

}
