package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private final ProductCategoryService categoryService;

    @Autowired
    public CustomErrorController(ProductCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, Model model) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("categories", categoryService.getAllCategories());

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorCode", "404");
                model.addAttribute("errorMessage", "Page not found ");
            }

            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorCode", "500");
                model.addAttribute("errorMessage", "Internal server error");
            }
        }

        return modelAndView;
    }

}