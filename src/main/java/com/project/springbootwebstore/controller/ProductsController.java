package com.project.springbootwebstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.service.ProductCategoryService;
import com.project.springbootwebstore.model.service.ProductService;
import com.project.springbootwebstore.model.service.ProductSubcategoryService;
import com.project.springbootwebstore.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;

//TODO: reviews, table constraints, security, signup/login, confirmation of email, pagination, clean code, separate controllers, slider size, hamburger, favorites, crud for products

@Controller
@RequestMapping("/main")
public class ProductsController {

    ProductService productService;
    ProductCategoryService categoryService;
    ProductSubcategoryService subcategoryService;
    UserService userService;

    @Autowired
    public ProductsController(ProductService productService, ProductCategoryService categoryService, ProductSubcategoryService subcategoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
        this.userService = userService;
    }

    @PostMapping("/cart-items")
    public @ResponseBody List<Product> cartItems(@RequestBody String some) throws IOException {
        System.out.println(some);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<JsonProduct> someClassList = objectMapper.readValue(some, typeFactory.constructCollectionType(List.class, JsonProduct.class));
        someClassList.forEach(System.out::println);
        List<Product> responseProducts = new ArrayList<>();
        for (JsonProduct jProd: someClassList) {
            Product product = productService.getProductById(jProd.getId());
            responseProducts.add(product);
        }
        System.out.println("================================");
        responseProducts.forEach(System.out::println);
//        return ResponseEntity.ok().body("{call : success}");
        Product p = productService.getProductById(1L);
        byte[] byteData = Files.readAllBytes(Paths.get("C:\\Users\\PC\\Desktop\\My Projects\\springboot-web-store\\src\\main\\resources\\static\\images\\iphone14.jpg"));
        String base64String = Base64.getEncoder().encodeToString(byteData);
        String img = "/images/iphone14.jpg";
        System.out.println(base64String);
        return responseProducts;
    }


    //прочитать заголовки
    @GetMapping(value = "/get-headers")
    public ResponseEntity<?> getHeaders(@RequestHeader Map<String, String> headers){//представляет заголовки ввиде мапы,
        //где ключ это наименование заголовка, а значение мапы - это значение заголовка
        return ResponseEntity.ok(headers);
    }

    @GetMapping(value = "/cart")
    public ModelAndView cartView(){
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        categoryService.getAllCategories().forEach(System.out::println);
        return modelAndView;
    }

    @GetMapping(value = "/account")
    public ModelAndView accountView(){
        ModelAndView modelAndView = new ModelAndView("account");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        categoryService.getAllCategories().forEach(System.out::println);
        return modelAndView;
    }

    @GetMapping(value = "/favorites")
    public ModelAndView favoritesView(){
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        categoryService.getAllCategories().forEach(System.out::println);
        return modelAndView;
    }

    @GetMapping
    public ModelAndView mainView() {
        System.out.println(userService.getUserByUsername("admin"));
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        categoryService.getAllCategories().forEach(System.out::println);
        return modelAndView;
    }

    @GetMapping("/{category}/{subcategory}")
    public ModelAndView subcategoryView(@RequestParam(name = "page") int page, @PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory) {
        ProductSubcategory subcategory1 = subcategoryService.getSubcategoryByName(subcategory);
        System.out.println(subcategory1);
        Page<Product> productPages = productService.getProductPages(page, 2, subcategory1.getId());
        List<Product> products = productPages.getContent();
        ModelAndView mov = new ModelAndView("products");

        mov.addObject("currentPage", page);
        mov.addObject("products", products);
        mov.addObject("totalPages", productPages.getTotalPages());
        mov.addObject("totalProducts", productPages.getTotalElements());
        mov.addObject("categories", categoryService.getAllCategories());
        return mov;
    }


    @GetMapping("/{category}/{subcategory}/{productId}")
    public ModelAndView productView(@PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory, @PathVariable(name = "productId") Long productId) {
        Product product = productService.getProductById(productId);
        ModelAndView mov = new ModelAndView("product-view");
        mov.addObject("product", product);
        mov.addObject("categories", categoryService.getAllCategories());
        return mov;
    }

}
