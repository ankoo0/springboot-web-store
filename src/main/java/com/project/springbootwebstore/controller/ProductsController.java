package com.project.springbootwebstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.springbootwebstore.model.dto.ProductDto;
import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.service.ProductCategoryService;
import com.project.springbootwebstore.model.service.ProductService;
import com.project.springbootwebstore.model.service.ProductSubcategoryService;
import com.project.springbootwebstore.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

   private final ProductService productService;
   private final ProductCategoryService categoryService;
   private final ProductSubcategoryService subcategoryService;
   private final UserService userService;

    @Autowired
    public ProductsController(ProductService productService, ProductCategoryService categoryService, ProductSubcategoryService subcategoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ModelAndView allSearchResults (@RequestParam(name = "q", required = false) Optional<String> query,@RequestParam(name = "page",defaultValue = "1") int page){
        ModelAndView modelAndView = new ModelAndView("products");
        Page<ProductDto> productPages = productService.search(query.orElse(""), PageRequest.of(page-1,2));
        List<ProductDto> products = productPages.getContent();
        System.out.println(products.size());
        productPages.forEach(System.out::println);
        System.out.println(productPages.getTotalPages());
        System.out.println("======================");
        modelAndView.addObject("query", query.orElse(""));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("products", products);
        modelAndView.addObject("totalPages", productPages.getTotalPages());
        modelAndView.addObject("totalProducts", productPages.getTotalElements());
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }

    @PostMapping("/cart-items")
    public @ResponseBody List<ProductDto> cartItems(@RequestBody String some) throws IOException {
        System.out.println(some);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<JsonProduct> someClassList = objectMapper.readValue(some, typeFactory.constructCollectionType(List.class, JsonProduct.class));
        someClassList.forEach(System.out::println);
        List<ProductDto> responseProducts = new ArrayList<>();
        for (JsonProduct jProd: someClassList) {
            ProductDto product = productService.getProductById(jProd.getId());
            responseProducts.add(product);
        }
        System.out.println("================================");
        responseProducts.forEach(System.out::println);
//        return ResponseEntity.ok().body("{call : success}");
        ProductDto p = productService.getProductById(1L);
        byte[] byteData = Files.readAllBytes(Paths.get("C:\\Users\\PC\\Desktop\\My Projects\\springboot-web-store\\src\\main\\resources\\static\\images\\iphone14.jpg"));
        String base64String = Base64.getEncoder().encodeToString(byteData);
        String img = "/images/iphone14.jpg";
        System.out.println(base64String);
        return responseProducts;
    }

//
//    //прочитать заголовки
//    @GetMapping(value = "/get-headers")
//    public ResponseEntity<?> getHeaders(@RequestHeader Map<String, String> headers){//представляет заголовки ввиде мапы,
//        //где ключ это наименование заголовка, а значение мапы - это значение заголовка
//        return ResponseEntity.ok(headers);
//    }

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
    public ModelAndView subcategoryView(
            @RequestParam(name = "order", required = false) String order,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "page") int page,
            @PathVariable(name = "category") String category,
            @PathVariable(name = "subcategory") String subcategoryName) {
        ProductSubcategory subcategory = subcategoryService.getSubcategoryByName(subcategoryName);
        Page<ProductDto> productPages;
        // map filter gri sort by query page and other put inside db query @RequestParam Map<String,String> map,
//        map.entrySet().forEach(System.out::println);
        System.out.println(page);
        if (query!=null){
           productPages = productService.getProductPagesByQuery(page, 5, subcategory.getId(),query);
        } else {
            productPages = productService.getProductPages(page, 5, subcategory.getId());
        }
        List<ProductDto> products = productPages.getContent();
        ModelAndView mov = new ModelAndView("products");
        mov.addObject("sortBy",sortBy);
        mov.addObject("order",order);
        mov.addObject("query", "");
        mov.addObject("currentPage", page);
        mov.addObject("products", products);
        mov.addObject("totalPages", productPages.getTotalPages());
        mov.addObject("totalProducts", productPages.getTotalElements());
        mov.addObject("categories", categoryService.getAllCategories());
        return mov;

    }


    @GetMapping("/{category}/{subcategory}/{productId}")
    public ModelAndView productView(@PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory, @PathVariable(name = "productId") Long productId) {
        ProductDto product = productService.getProductById(productId);
        ModelAndView mov = new ModelAndView("product-view");
        mov.addObject("product", product);
        mov.addObject("categories", categoryService.getAllCategories());
        return mov;
    }

}
