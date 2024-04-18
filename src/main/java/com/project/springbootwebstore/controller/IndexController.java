package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import static com.project.springbootwebstore.controller.constants.CatalogUrlConstants.CATALOG;

@Controller("/")
@RequiredArgsConstructor
public class IndexController {

    private final ProductCategoryService categoryService;

    @GetMapping
    public ModelAndView mainView() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("categories", categoryService.getAllCategories());
        mav.addObject("name", "Aliaksandr");
        mav.addObject("basePath", CATALOG);
        return mav;
    }
}
