package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.FavoriteItem;
import com.project.springbootwebstore.dto.product.ProductShortInfoResponse;
import com.project.springbootwebstore.service.ProductCategoryService;
import com.project.springbootwebstore.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final ProductServiceImpl productService;
    private final ProductCategoryService categoryService;

    @PostMapping
    public @ResponseBody List<ProductShortInfoResponse> favoriteItems(@RequestBody List<FavoriteItem> items) {

        List<ProductShortInfoResponse> responseProducts = new ArrayList<>();
        for (FavoriteItem item : items) {
            ProductShortInfoResponse product = productService.getSingleFavoriteProduct(item);
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
