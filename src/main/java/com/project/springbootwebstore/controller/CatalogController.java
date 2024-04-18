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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static com.project.springbootwebstore.controller.constants.CatalogUrlConstants.*;
import static com.project.springbootwebstore.controller.constants.ViewConstants.*;

@Controller
@RequestMapping(CATALOG)
@RequiredArgsConstructor
public class CatalogController {

    /**
     * Default page sorting is rating - not implemented actually
     * Finish email service
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
    public ModelAndView allSearchResults(
            @RequestParam(name = "q", required = false) Optional<String> query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            HttpServletRequest servletRequest
    ) {

        Page<ProductShortInfoResponse> productPages = productService.search(query.orElse(""), PageRequest.of(page - 1, 2));
        List<ProductShortInfoResponse> products = productPages.getContent();

        ModelAndView mav = new ModelAndView(PRODUCTS);
        mav.addObject("query", query.orElse(""));
        mav.addObject("currentPage", page);
        mav.addObject("products", products);
        mav.addObject("totalPages", productPages.getTotalPages());
        mav.addObject("totalProducts", productPages.getTotalElements());
        mav.addObject("categories", categoryService.getAllCategories());
        mav.addObject("contextPath", servletRequest.getContextPath());
        return mav;
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
            HttpServletRequest servletRequest
    ) {

        Map<String, String[]> map = productService.getParametersMap(params);
        String parameters = productService.getFilterParamsString(map);
        Page<ProductShortInfoResponse> productPages = productService.getProductPage(map, subcategoryName);
        List<ProductShortInfoResponse> products = productPages.getContent();

        int totalPages = productPages.getTotalPages();
        int paginationStart = productService.getPaginationStart(page);
        int paginationEnd = productService.getPaginationEnd(page, totalPages);

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

    @GetMapping(GET_PRODUCT)
    public ModelAndView productView(
            @PathVariable(name = "category") String category,
            @PathVariable(name = "subcategory") String subcategory,
            @PathVariable(name = "productId") Long productId,
            HttpServletRequest servletRequest
    ) {
        ProductFullInfoResponse product = productService.getProductById(productId);
        Long productReviewsCount = reviewService.getProductReviewsCount(productId);

        ModelAndView mav = new ModelAndView(PRODUCT_VIEW);
        mav.addObject("product", product);
        mav.addObject("categories", categoryService.getAllCategories());
        mav.addObject("reviewCount", productReviewsCount);
        mav.addObject("basePath", CATALOG);
        mav.addObject("contextPath", servletRequest.getContextPath());

        return mav;
    }

}
