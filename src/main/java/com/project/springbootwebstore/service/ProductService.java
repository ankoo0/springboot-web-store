package com.project.springbootwebstore.service;


import com.project.springbootwebstore.dto.*;
import com.project.springbootwebstore.dto.mappers.ProductToListViewDtoMapper;
import com.project.springbootwebstore.dto.mappers.ProductToProductViewDtoMapper;
import com.project.springbootwebstore.entity.product.Product;
import com.project.springbootwebstore.entity.product.ProductSubcategory;
import com.project.springbootwebstore.repository.CustomProductRepository;
import com.project.springbootwebstore.repository.ProductRepository;
import com.project.springbootwebstore.repository.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomProductRepository customProductRepository;
    private final ProductSubcategoryService subcategoryService;
    private final ProductToListViewDtoMapper productToListViewDtoMapper;
    private final ProductToProductViewDtoMapper productToProductViewDtoMapper;

    public ProductToProductViewDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productToProductViewDtoMapper.apply(product);
    }

    public ProductToListViewDto getSingleCartProduct(CartItem item) {
        Product product = productRepository.findById((long) item.id()).orElseThrow();
        Long discount = product.getDiscount().getDiscountPercent();
        Double price = product.getPrice();
        DecimalFormat df = new DecimalFormat("#.##");
        Double actualPriceByQuantity = (discount > 0 ? Double.valueOf(price - ((price / 100) * discount)) : price) * item.quantity();
        product.setPrice(Double.valueOf(df.format(actualPriceByQuantity)));
        return productToListViewDtoMapper.apply(product);
    }


    public ProductToListViewDto getSingleFavoriteProduct(FavoriteItem item) {
        Product product = productRepository.findById((long) item.getId()).orElseThrow();

        return productToListViewDtoMapper.apply(product);
    }


    public Page<Product> filter(Map<String, String[]> filteringAttributes, String subcategory) {
        return productRepository.findAll(ProductSpecification.hasAttributes(filteringAttributes), PageRequest.of(1, 10));
    }

    public Map<Long, ProductSubcategoryDto> countProductOccurrences(String query) {
        Map<Long, ProductSubcategoryDto> occurrences = new HashMap<>();
        List<ProductSubcategory> subcategories = subcategoryService.getAllSubcategories();
        List<ProductSubcategoryDto> subcategoryDtoList = subcategories.stream().map(s ->
                new ProductSubcategoryDto.Builder()
                        .setId(s.getId())
                        .setCategory(s.getProductCategory().getCategoryName())
                        .setName(s.getSubcategoryName())
                        .build()
        ).toList();

        for (var subcategory : subcategoryDtoList) {
            Long count = productRepository.countAllBySubcategory(query, subcategory.getId());
            occurrences.put(count, subcategory);
        }

        return occurrences.entrySet().stream()
                .filter(e -> e.getKey() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Page<ProductToListViewDto> getProductPages(Map<String, String[]> parameters, String subcategoryName) {

        Map<String, String[]> filter = parameters.entrySet()
                .stream()
                .filter(e -> !e.getKey().equals("page") &&
                        !e.getKey().equals("sortBy") &&
                        !e.getKey().equals("order") &&
                        !e.getKey().equals("q"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        Specification<Product> spec = Specification.where(null);
        Sort sort = Sort.unsorted();

        boolean hasOrder = parameters.containsKey("order");
        boolean hasSorting = parameters.containsKey("sortBy");
        boolean hasQuery = parameters.containsKey("q");
        boolean hasFilter = !filter.isEmpty();


        if (hasFilter) {
            spec = ProductSpecification.hasAttributes(filter);

        }
        if (hasSorting) {
            String sortBy = parameters.get("sortBy")[0];
            String order = hasOrder ? parameters.get("order")[0] : "";
            sort = ProductSpecification.sort(sortBy, order);

        }
        if (hasQuery) {
            String query = parameters.get("q")[0];
            spec = spec.and(ProductSpecification.hasQueryy(query));

        }

        spec = spec.and(ProductSpecification.hasSubcategory(subcategoryName));


        int page = Integer.parseInt(parameters.get("page")[0]);
        Pageable pageable = PageRequest.of(page - 1, 10, sort);

        Page<Product> productPage = productRepository.findAll(spec, pageable);

        return productPage.map(productToListViewDtoMapper);
    }


    public Page<ProductToListViewDto> search(String query, Pageable pageable) {
        Page<Product> pages = productRepository.getAllByName(query, pageable);
        return pages.map(productToListViewDtoMapper);
    }

    public Double getProductCartPrice(CartItem cartItem) {

        ProductToListViewDto product = productToListViewDtoMapper.apply(productRepository.findById((long) cartItem.id()).get());

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(product.price() * cartItem.quantity()));
    }

    public List<Product> getAllProductsById(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }
}
