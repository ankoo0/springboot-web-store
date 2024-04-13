package com.project.springbootwebstore.controller;

import com.project.springbootwebstore.dto.AttributeResponse;
import com.project.springbootwebstore.dto.ProductToListViewDto;
import com.project.springbootwebstore.dto.ProductToProductViewDto;
import com.project.springbootwebstore.service.*;
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

import static com.project.springbootwebstore.controller.Views.*;

@Controller
@RequestMapping("/main")
@RequiredArgsConstructor
public class ProductsController {

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


    private final ProductService productService;
    private final ProductCategoryService categoryService;
    private final ProductSubcategoryService subcategoryService;
    private final ProductAttributeService attributeService;
    private final UserService userService;
    private final ReviewService reviewService;

    @GetMapping("/all")
    public ModelAndView allSearchResults(@RequestParam(name = "q", required = false) Optional<String> query, @RequestParam(name = "page", defaultValue = "1") int page, HttpServletRequest servletRequest) {
        ModelAndView modelAndView = new ModelAndView(PRODUCTS);

        Page<ProductToListViewDto> productPages = productService.search(query.orElse(""), PageRequest.of(page - 1, 2));
        List<ProductToListViewDto> products = productPages.getContent();
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
    public @ResponseBody List<AttributeResponse> getFilter(@RequestBody String subcategory) {
        return attributeService.getAttributesBySubcategory(subcategory);
    }

    @GetMapping
    public ModelAndView mainView() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryService.getAllCategories());
        modelAndView.addObject("name", "Aliaksandr");
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
            @PathVariable(name = "subcategory") String subcategoryName,
            HttpServletRequest servletRequest) {
        Page<ProductToListViewDto> productPages;

        Map<String, String[]> map = params.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e2 -> e2.getValue().toArray(new String[0])));
        String parameters = getFilterParamsString(map);
        productPages = productService.getProductPages(map, subcategoryName);
        List<ProductToListViewDto> products = productPages.getContent();

        query = query == null ? "" : query;
        int totalPages = productPages.getTotalPages();
        int paginationStart = getPaginationStart(page);
        int paginationEnd = getPaginationEnd(page, totalPages);

        ModelAndView mov = new ModelAndView(PRODUCTS);
        mov.addObject("parameters", parameters);
        mov.addObject("sortBy", sortBy);
        mov.addObject("order", order);
        mov.addObject("query", query);
        mov.addObject("currentPage", page);
        mov.addObject("totalPages", totalPages);
        mov.addObject("products", products);
        mov.addObject("subcategory", StringUtils.capitalize(subcategoryName));
        mov.addObject("paginationStart", paginationStart);
        mov.addObject("paginationEnd", paginationEnd);
        mov.addObject("totalProducts", productPages.getTotalElements());
        mov.addObject("categories", categoryService.getAllCategories());
        mov.addObject("contextPath", servletRequest.getContextPath());

        return mov;

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

    @GetMapping("/{category}/{subcategory}/{productId}")
    public ModelAndView productView(@PathVariable(name = "category") String category, @PathVariable(name = "subcategory") String subcategory, @PathVariable(name = "productId") Long productId, HttpServletRequest servletRequest) {
        ProductToProductViewDto product = productService.getProductById(productId);
        Long productReviewsCount = reviewService.getProductReviewsCount(productId);
        ModelAndView mov = new ModelAndView(PRODUCT_VIEW);
        mov.addObject("product", product);
        mov.addObject("categories", categoryService.getAllCategories());
        mov.addObject("reviewCount", productReviewsCount);
        mov.addObject("contextPath", servletRequest.getContextPath());

        return mov;
    }

}
