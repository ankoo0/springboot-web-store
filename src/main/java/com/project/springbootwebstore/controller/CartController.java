package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.CartItem;
import com.project.springbootwebstore.dto.product.ProductShortInfoResponse;
import com.project.springbootwebstore.service.EmailService;
import com.project.springbootwebstore.service.ProductCategoryService;
import com.project.springbootwebstore.service.impl.ProductServiceImpl;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final ProductServiceImpl productService;
    private final ProductCategoryService categoryService;
    private final EmailService emailService;

    @PostMapping("/items")
    public @ResponseBody List<ProductShortInfoResponse> cartItems(@RequestBody List<CartItem> items) {

        List<ProductShortInfoResponse> responseProducts = new ArrayList<>();
        for (CartItem item : items) {
            ProductShortInfoResponse product = productService.getSingleCartProduct(item);
            responseProducts.add(product);
        }
        return responseProducts;
    }

    @GetMapping
    public ModelAndView cartView() throws MessagingException, IOException {
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
