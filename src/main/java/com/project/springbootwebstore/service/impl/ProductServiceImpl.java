package com.project.springbootwebstore.service.impl;

import com.project.springbootwebstore.dto.CartItem;
import com.project.springbootwebstore.dto.FavoriteItem;
import com.project.springbootwebstore.dto.product.ProductSubcategoryResponse;
import com.project.springbootwebstore.dto.product.ProductFullInfoResponse;
import com.project.springbootwebstore.dto.product.ProductShortInfoResponse;
import com.project.springbootwebstore.entity.product.Product;
import com.project.springbootwebstore.entity.product.ProductSubcategory;
import com.project.springbootwebstore.repository.ProductRepository;
import com.project.springbootwebstore.repository.specification.ProductSpecification;
import com.project.springbootwebstore.service.ProductService;
import com.project.springbootwebstore.service.ProductSubcategoryService;
import com.project.springbootwebstore.service.UrlAttributesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.project.springbootwebstore.dto.product.ProductMapper.PRODUCT_MAPPER;
import static com.project.springbootwebstore.service.UrlAttributesConstants.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductSubcategoryService subcategoryService;

    @Override
    public ProductFullInfoResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return PRODUCT_MAPPER.toFullInfoResponse(product);
    }

    @Override
    public ProductShortInfoResponse getSingleCartProduct(CartItem item) {
        Product product = productRepository.findById(item.id()).orElseThrow();
        Long discount = product.getDiscount().getDiscountPercent();
        Double price = product.getPrice();
        DecimalFormat df = new DecimalFormat("#.##");
        Double actualPriceByQuantity = (discount > 0 ? Double.valueOf(price - ((price / 100) * discount)) : price) * item.quantity();
        product.setPrice(Double.valueOf(df.format(actualPriceByQuantity)));
        return PRODUCT_MAPPER.toShortInfoResponse(product);
    }

    @Override
    public ProductShortInfoResponse getSingleFavoriteProduct(FavoriteItem item) {
        Product product = productRepository.findById(item.getId()).orElseThrow();

        return PRODUCT_MAPPER.toShortInfoResponse(product);
    }

    @Override
    public Page<Product> filter(Map<String, String[]> filteringAttributes, String subcategory) {
        return productRepository.findAll(ProductSpecification.hasAttributes(filteringAttributes), PageRequest.of(1, 10));
    }

    @Override
    public Map<Long, ProductSubcategoryResponse> countProductOccurrences(String query) {
        Map<Long, ProductSubcategoryResponse> occurrences = new HashMap<>();
        List<ProductSubcategory> subcategories = subcategoryService.getAllSubcategories();
        List<ProductSubcategoryResponse> subcategoryDtoList =
                subcategories.
                        stream()
                        .map(PRODUCT_MAPPER::toSubcategoryResponse)
                        .toList();

        for (var subcategory : subcategoryDtoList) {
            Long count = productRepository.countAllBySubcategory(query, subcategory.id());
            occurrences.put(count, subcategory);
        }

        return occurrences.entrySet().stream()
                .filter(e -> e.getKey() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public String getFilterParamsString(Map<String, String[]> params) {
        return params.entrySet()
                .stream()
                .filter(e ->
                        !e.getKey().equals(PAGE) &&
                                !e.getKey().equals(SORT_BY) &&
                                !e.getKey().equals(ORDER) &&
                                !e.getKey().equals(QUERY))
                .map(e -> Arrays.stream(e.getValue()).map(val -> e.getKey() + "=" + val).toList())
                .map(param -> String.join("&", param))
                .collect(Collectors.joining("&"));
    }


    @Override
    public int getPaginationStart(int currentPage) {
        return switch (currentPage) {
            case (1), (2) -> 1;
            default -> currentPage - 2;
        };

    }
    @Override
    public int getPaginationEnd(int currentPage, int totalPages) {
        int result = totalPages - currentPage;
        return switch (result) {
            case (0), (1) -> totalPages;
            default -> currentPage + 2;
        };

    }

    @Override
    public Map<String, String[]> getParametersMap(MultiValueMap<String, String> params) {
        return params
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e2 -> e2.getValue().toArray(new String[0])));
    }


    @Override
    public Page<ProductShortInfoResponse> getProductPage(Map<String, String[]> parameters, String subcategoryName) {

        Map<String, String[]> filter = parameters.entrySet()
                .stream()
                .filter(e -> !e.getKey().equals(PAGE) &&
                        !e.getKey().equals(SORT_BY) &&
                        !e.getKey().equals(ORDER) &&
                        !e.getKey().equals(QUERY))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        Specification<Product> spec = Specification.where(null);
        Sort sort = Sort.by("rating").descending();

        boolean hasOrder = parameters.containsKey(ORDER);
        boolean hasSorting = parameters.containsKey(SORT_BY);
        boolean hasQuery = parameters.containsKey(QUERY);
        boolean hasFilter = !filter.isEmpty();


        if (hasFilter) {
            spec = ProductSpecification.hasAttributes(filter);

        }
        if (hasSorting) {
            String sortBy = parameters.get(SORT_BY)[0];
            String order = hasOrder ? parameters.get(ORDER)[0] : "";
            sort = ProductSpecification.sort(sortBy, order);

        }
        if (hasQuery) {
            String query = parameters.get(QUERY)[0];
            spec = spec.and(ProductSpecification.hasQuery(query));

        }

        spec = spec.and(ProductSpecification.hasSubcategory(subcategoryName));

        int page = Integer.parseInt(parameters.get(PAGE)[0]);
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        return productPage.map(PRODUCT_MAPPER::toShortInfoResponse);
    }

    @Override
    public Page<ProductShortInfoResponse> search(String query, Pageable pageable) {
        Page<Product> pages = productRepository.getAllByName(query, pageable);
        return pages.map(PRODUCT_MAPPER::toShortInfoResponse);
    }

    @Override
    public Double getProductCartPrice(CartItem cartItem) {

        ProductShortInfoResponse product = PRODUCT_MAPPER.toShortInfoResponse(productRepository.findById(cartItem.id()).get());

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(product.price() * cartItem.quantity()));
    }

    @Override
    public List<Product> getAllProductsById(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }
}
