package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.project.springbootwebstore.controller.constants.CatalogUrlConstants.CATALOG;

@Controller("/")
@RequiredArgsConstructor
public class IndexController {

    private final ProductCategoryService categoryService;

    @GetMapping
    public ModelAndView mainView() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("name", "Aliaksandr");
        modelAndView.addObject("basePath", CATALOG);
        return modelAndView;
    }
}
