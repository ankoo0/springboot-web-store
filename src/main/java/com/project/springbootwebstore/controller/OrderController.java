package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.OrderDto;
import com.project.springbootwebstore.security.CustomUserDetails;
import com.project.springbootwebstore.service.OrderService;
import com.project.springbootwebstore.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final ProductCategoryService categoryService;
    private final OrderService orderService;

    @GetMapping()
    public ModelAndView getOrderView() {
        ModelAndView modelAndView = new ModelAndView("order");
        modelAndView.addObject("categories", categoryService.getAllCategories());

        return modelAndView;
    }

    @PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE)
    public String acceptOrder(@RequestBody OrderDto orderDto, Authentication authentication, RedirectAttributes redirectAttributes){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
         orderService.saveOrder(orderDto,userDetails);
        redirectAttributes.addFlashAttribute("categories", categoryService.getAllCategories());

        return "redirect:/cart";
    }
}
