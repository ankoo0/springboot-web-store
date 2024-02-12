package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.CartItem;
import com.project.springbootwebstore.dto.ProductToListViewDto;
import com.project.springbootwebstore.service.EmailService;
import com.project.springbootwebstore.service.ProductCategoryService;
import com.project.springbootwebstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;
    private final ProductCategoryService categoryService;
    private final EmailService emailService;

    @PostMapping("/items")
    public @ResponseBody List<ProductToListViewDto> cartItems(@RequestBody List<CartItem> items) {

        List<ProductToListViewDto> responseProducts = new ArrayList<>();
        for (CartItem item : items) {
            ProductToListViewDto product = productService.getSingleCartProduct(item);
            responseProducts.add(product);
        }
        return responseProducts;
    }

    @GetMapping
    public ModelAndView cartView() {
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        emailService.sendSimpleMessage("an516293@gmail.com","Test", "Hello World");
        return modelAndView;
    }

    @PostMapping("/price")
    public @ResponseBody String getProductPrice(@RequestBody CartItem product){
        return productService.getProductCartPrice(product).toString();
    }
}
