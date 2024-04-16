package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.AttributeResponse;
import com.project.springbootwebstore.dto.product.ProductShortInfoResponse;
import com.project.springbootwebstore.dto.product.ProductFullInfoResponse;
import com.project.springbootwebstore.service.*;
import com.project.springbootwebstore.service.impl.ProductServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.project.springbootwebstore.controller.constants.CatalogUrlConstants.*;
import static com.project.springbootwebstore.controller.constants.ViewConstants.*;

@Controller
@RequestMapping(CATALOG)
@RequiredArgsConstructor
public class CatalogController {

    /**
     * Fix Form and Sorting
     * Add Review modal
     * Refactor CSS
     * Refactor JS
     * Refactor HTML
     * Make JS Reusable
     * Upload Images
     * Rating Hover
     * Account
     * Admin
     * Favorites
     * Review Sorting
     * Submit Order
     * User Reselling with WebSocket Chat
     * RWD
     * Detect Offensive Words
     * Email Notifications
     * User Verification
     * Transitions on Page Load
     * You May Also Like
     * Comparison
     * Category View with Go-to-subcategory filter
     * Previous Page Navigation
     * Search Modal
     * Fullscreen Slider
     * Refine Hamburger Menu
     * Admin PDF functionality
     * Add Product
     * Why init cascade animation works differently depending on placement?
     */


    private final ProductServiceImpl productService;
    private final ProductCategoryService categoryService;
    private final ProductAttributeService attributeService;
    private final ReviewService reviewService;

    @GetMapping("/all")
    public ModelAndView allSearchResults(@RequestParam(name = "q", required = false) Optional<String> query, @RequestParam(name = "page", defaultValue = "1") int page, HttpServletRequest servletRequest) {
        ModelAndView modelAndView = new ModelAndView(PRODUCTS);

        Page<ProductShortInfoResponse> productPages = productService.search(query.orElse(""), PageRequest.of(page - 1, 2));
        List<ProductShortInfoResponse> products = productPages.getContent();
        modelAndView.addObject("query", query.orElse(""));
        modelAndView.addObject("currentPage", page);
        modelAndView.addObject("products", products);
        modelAndView.addObject("totalPages", productPages.getTotalPages());
        modelAndView.addObject("totalProducts", productPages.getTotalElements());
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("contextPath", servletRequest.getContextPath());
        return modelAndView;
    }

    @PostMapping("/filter")
    public @ResponseBody List<AttributeResponse> getProductFilter(@RequestBody String subcategory) {
        return attributeService.getAttributesBySubcategory(subcategory);
    }



    @GetMapping(GET_PRODUCTS)
    public ModelAndView subcategoryView(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "order", required = false) String order,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "q", required = false, defaultValue = "") String query,
            @RequestParam(name = "page") int page,
            @PathVariable(name = "category") String category,
            @PathVariable(name = "subcategory") String subcategoryName,
            HttpServletRequest servletRequest) {


        Map<String, String[]> map = params.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e2 -> e2.getValue().toArray(new String[0])));
        String parameters = getFilterParamsString(map);
        Page<ProductShortInfoResponse> productPages = productService.getProductPage(map, subcategoryName);
        List<ProductShortInfoResponse> products = productPages.getContent();

        int totalPages = productPages.getTotalPages();
        int paginationStart = getPaginationStart(page);
        int paginationEnd = getPaginationEnd(page, totalPages);


        ModelAndView mav = new ModelAndView(PRODUCTS);
        mav.addObject("parameters", parameters);
        mav.addObject("sortBy", sortBy);
        mav.addObject("order", order);
        mav.addObject("query", query);
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", totalPages);
        mav.addObject("products", products);
        mav.addObject("subcategory", StringUtils.capitalize(subcategoryName));
        mav.addObject("paginationStart", paginationStart);
        mav.addObject("paginationEnd", paginationEnd);
        mav.addObject("totalProducts", productPages.getTotalElements());
        mav.addObject("categories", categoryService.getAllCategories());
        mav.addObject("basePath", CATALOG);
        mav.addObject("contextPath", servletRequest.getContextPath());

        return mav;

    }

    private String getFilterParamsString(Map<String, String[]> params) {
        return params.entrySet()
                .stream()
                .filter(e ->
                        !e.getKey().equals("page") &&
                        !e.getKey().equals("sortBy") &&
                        !e.getKey().equals("order") &&
                        !e.getKey().equals("q"))
                .map(e -> Arrays.stream(e.getValue()).map(val -> e.getKey() + "=" + val).toList())
                .map(param -> String.join("&", param))
                .collect(Collectors.joining("&"));
    }

    private int getPaginationStart(int currentPage) {
        return switch (currentPage) {
            case (1), (2) -> 1;
            default -> currentPage - 2;
        };

    }

    private int getPaginationEnd(int currentPage, int totalPages) {
        int result = totalPages - currentPage;
        return switch (result) {
            case (0), (1) -> totalPages;
            default -> currentPage + 2;
        };

    }

    @GetMapping(GET_PRODUCT)
    public ModelAndView productView(@PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory, @PathVariable(name = "productId") Long productId, HttpServletRequest servletRequest) {
        ProductFullInfoResponse product = productService.getProductById(productId);
        Long productReviewsCount = reviewService.getProductReviewsCount(productId);
        ModelAndView mov = new ModelAndView(PRODUCT_VIEW);
        mov.addObject("product", product);
        mov.addObject("categories", categoryService.getAllCategories());
        mov.addObject("reviewCount", productReviewsCount);
        mov.addObject("contextPath", servletRequest.getContextPath());

        return mov;
    }

}
