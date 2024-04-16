//package com.project.springbootwebstore.controller;
//
//import com.project.springbootwebstore.service.ProductCategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller("/account")
//public class AccountController {
//
//    // TODO: Validation, Package Refactoring, CSS and Template Refactoring, Delete Comments, Reuse Constants and Code
//
//    private final ProductCategoryService categoryService;
//
//    @Autowired
//    public AccountController(ProductCategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping
//    public ModelAndView accountView() {
//        ModelAndView modelAndView = new ModelAndView("account");
//        modelAndView.addObject("categories", categoryService.getAllCategories());
//
//        return modelAndView;
//    }
//
//    @GetMapping("/orders")
//    public ModelAndView userOrdersView(){
//        ModelAndView modelAndView = new ModelAndView("account");
//        modelAndView.addObject("categories", categoryService.getAllCategories());
//
//        return null;
//    }
//}
