package com.project.springbootwebstore.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.project.springbootwebstore.model.dto.ProductDto;
import com.project.springbootwebstore.model.dto.ProductToListViewDto;
import com.project.springbootwebstore.model.dto.ProductToProductViewDto;
import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Base64Utils;
import org.springframework.util.MultiValueMap;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;




@Controller
@RequestMapping("/main")
public class ProductsController {



   private final ProductService productService;
   private final ProductCategoryService categoryService;
   private final ProductSubcategoryService subcategoryService;
   private final ProductAttributeService attributeService;
   private final UserService userService;
   private final ReviewService reviewService;

   @Autowired
    public ProductsController(ProductService productService, ProductCategoryService categoryService, ProductSubcategoryService subcategoryService, ProductAttributeService attributeService, UserService userService, ReviewService reviewService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.subcategoryService = subcategoryService;
        this.attributeService = attributeService;
        this.userService = userService;
        this.reviewService = reviewService;
    }



    @GetMapping("/all")
    public ModelAndView allSearchResults (@RequestParam(name = "q", required = false) Optional<String> query,@RequestParam(name = "page",defaultValue = "1") int page){
        ModelAndView modelAndView = new ModelAndView("products");

        Page<ProductToListViewDto> productPages = productService.search(query.orElse(""), PageRequest.of(page-1,2));
        List<ProductToListViewDto> products = productPages.getContent();

        modelAndView.addObject("query", query.orElse(""));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("products", products);
        modelAndView.addObject("totalPages", productPages.getTotalPages());
        modelAndView.addObject("totalProducts", productPages.getTotalElements());
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }

    @PostMapping("/cart-items")
    public @ResponseBody List<ProductToListViewDto> cartItems(@RequestBody String some) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<JsonProduct> someClassList = objectMapper.readValue(some, typeFactory.constructCollectionType(List.class, JsonProduct.class));

        List<ProductToListViewDto> responseProducts = new ArrayList<>();
        for (JsonProduct jProd: someClassList) {
            ProductToListViewDto product = productService.getSingleListViewProduct(jProd.getId());
            responseProducts.add(product);
        }

//        return ResponseEntity.ok().body("{call : success}");
//        ProductDto p = productService.getProductById(1L);
//        byte[] byteData = Files.readAllBytes(Paths.get("C:\\Users\\PC\\Desktop\\My Projects\\springboot-web-store\\src\\main\\resources\\static\\images\\iphone14.jpg"));
//        String base64String = Base64.getEncoder().encodeToString(byteData);
//        String img = "/images/iphone14.jpg";
//        System.out.println(base64String);
        return responseProducts;
    }

//
//    //прочитать заголовки
//    @GetMapping(value = "/get-headers")
//    public ResponseEntity<?> getHeaders(@RequestHeader Map<String, String> headers){//представляет заголовки ввиде мапы,
//        //где ключ это наименование заголовка, а значение мапы - это значение заголовка
//        return ResponseEntity.ok(headers);
//    }



    @PostMapping("/filter")
        public @ResponseBody Map<String, List<String>> getFilter(@RequestBody String subcategory ){
        System.out.println(subcategory);
        return  attributeService.getAttributesBySubcategory(subcategory);
        }

    @GetMapping(value = "/cart")
    public ModelAndView cartView(){

        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }

    @GetMapping(value = "/account")
    public ModelAndView accountView(){
        ModelAndView modelAndView = new ModelAndView("account");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }

    @GetMapping(value = "/favorites")
    public ModelAndView favoritesView(){
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }

    @GetMapping
    public ModelAndView mainView() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        return modelAndView;
    }


    @GetMapping("/{category}/{subcategory}")
    public ModelAndView subcategoryView(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "order", required = false) String order,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "page") int page,
            @PathVariable(name = "category") String category,
            @PathVariable(name = "subcategory") String subcategoryName
            ) {
        Page<ProductToListViewDto> productPages;

        params.forEach((e1,e2)-> System.out.println(e1 + "-" +e2 + " " + subcategoryName));


        ModelAndView mov = new ModelAndView("products");
        Map <String, String[]> map = params.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e2-> e2.getValue().toArray(new String[0])));


        String parameters = getFilterParamsString(map);
            productPages = productService.getProductPages(map,subcategoryName);
        List<ProductToListViewDto> products = productPages.getContent();
        query = query ==null ? "" : query;

        mov.addObject("parameters",parameters);
        mov.addObject("sortBy",sortBy);
        mov.addObject("order",order);
        mov.addObject("query", query);
        mov.addObject("currentPage", page);
        mov.addObject("products", products);
        mov.addObject("totalPages", productPages.getTotalPages());
        mov.addObject("totalProducts", productPages.getTotalElements());
        mov.addObject("categories", categoryService.getAllCategories());
        return mov;

    }

    private String getFilterParamsString( Map <String, String[]> params){
        return params.entrySet()
                .stream()
                .filter(e->!e.getKey().equals("page") &&
                        !e.getKey().equals("sortBy") &&
                        !e.getKey().equals("order") &&
                        !e.getKey().equals("q"))
                .map(e-> Arrays.stream(e.getValue()).map(val->e.getKey()+"="+val).toList())
                .map(param-> String.join("&", param))
                .collect(Collectors.joining("&"));
    }


    @GetMapping("/{category}/{subcategory}/{productId}")
    public ModelAndView productView(@PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory, @PathVariable(name = "productId") Long productId) {
        ProductToProductViewDto product = productService.getProductById(productId);
        Long productReviewsCount = reviewService.getProductReviewsCount(productId);
        ModelAndView mov = new ModelAndView("product-view");
        mov.addObject("product", product);
        mov.addObject("categories", categoryService.getAllCategories());
        mov.addObject("reviewCount",productReviewsCount);
        return mov;
    }

}
