package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductCategoryService categoryService;

    @GetMapping
    public ModelAndView admin(){
        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("categories", categoryService.getAllCategories());

        return modelAndView;
    }

    @GetMapping("/add-product")
    public ModelAndView addProduct(){
        ModelAndView modelAndView = new ModelAndView("add-product");
        modelAndView.addObject("categories", categoryService.getAllCategories());

        return modelAndView;
    }

}
