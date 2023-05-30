package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.dto.ProductDto;
import com.project.springbootwebstore.model.dto.ProductSubcategoryDto;
import com.project.springbootwebstore.model.dto.ProductToListViewDto;
import com.project.springbootwebstore.model.dto.ProductToProductViewDto;
import com.project.springbootwebstore.model.dto.mappers.ProductToListViewDtoMapper;
import com.project.springbootwebstore.model.dto.mappers.ProductToProductViewDtoMapper;
import com.project.springbootwebstore.model.entity.product.*;
import com.project.springbootwebstore.model.repository.CustomProductRepository;
import com.project.springbootwebstore.model.repository.ProductRepository;
import com.project.springbootwebstore.model.repository.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomProductRepository customProductRepository;
    private final ProductSubcategoryService subcategoryService;
    private final ProductToListViewDtoMapper productToListViewDtoMapper;
    private final ProductToProductViewDtoMapper productToProductViewDtoMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CustomProductRepository customProductRepository, ProductSubcategoryService subcategoryService, ProductToListViewDtoMapper productToListViewDtoMapper, ProductToProductViewDtoMapper productToProductViewDtoMapper) {
        this.productRepository = productRepository;
        this.customProductRepository = customProductRepository;
        this.subcategoryService = subcategoryService;
        this.productToListViewDtoMapper = productToListViewDtoMapper;
        this.productToProductViewDtoMapper = productToProductViewDtoMapper;
    }





    public ProductToProductViewDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productToProductViewDtoMapper.apply(product);
    }

    public ProductToListViewDto getSingleListViewProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return productToListViewDtoMapper.apply(product);
    }

//    public List<Product> getBySubcategoryId(Long subcategoryId){
//       return productRepository.findAllBySubcategoryId(subcategoryId);
//    }

//    public List<Product> getBySubcategory(String subcategoryName){
//        return productRepository.findAllBySubcategory(subcategoryName);
//    }

    public Page<Product> filter(Map<String,String[]> filteringAttributes, String subcategory) {
//        Specification<Product>  spec;
//        for (var val:attrValues){
//            spec = ProductSpecification.hasAttribute(val).or();
//        }
//ProductSpecification.hasAttributesAndSubcategory();
//        ProductSpecification.hasAttributesAndSubcategory(val).or()
        return productRepository.findAll(ProductSpecification.hasAttributes(filteringAttributes),PageRequest.of(1,10));
    }

    public Map<Long, ProductSubcategoryDto> countProductOcurrencies(String query) {
        Map<Long, ProductSubcategoryDto> ocurrencies = new HashMap<>();
        List<ProductSubcategory> subcategories = subcategoryService.getAllSubcategories();
        List<ProductSubcategoryDto> subcategoryDtos = subcategories.stream().map(s ->
                new ProductSubcategoryDto.Builder()
                        .setId(s.getId())
                        .setCategory(s.getProductCategory().getCategoryName())
                        .setName(s.getSubcategoryName())
                        .build()
        ).toList();

        for (var subcategory : subcategoryDtos) {
            Long count = productRepository.countAllBySubcategory(query, subcategory.getId());
            ocurrencies.put(count, subcategory);
        }

        return ocurrencies.entrySet().stream()
                .filter(e -> e.getKey() > 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Page<ProductToListViewDto> getProductPages(Map<String,String[]> parameters, String subcategoryName) {

        Map <String, String[]> filter = parameters.entrySet()
                .stream()
                .filter(e->!e.getKey().equals("page") &&
                        !e.getKey().equals("sortBy") &&
                        !e.getKey().equals("order") &&
                        !e.getKey().equals("q"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//        {
//            System.out.println("AAAAAAAA");
//            String query = parameters.get("q")[0];
//            Pageable pageable = PageRequest.of(1-1,10,Sort.unsorted());
//            productRepository.findAll(ProductSpecification.hasQueryy(query).and(ProductSpecification.hasAttributes(filter)),pageable).getContent().forEach(System.out::println);
//            System.out.println("AAAAAAAA");
//        }


        Specification<Product> spec = Specification.where(null);
        Sort sort = Sort.unsorted();

        boolean hasOrder = parameters.containsKey("order");
        boolean hasSorting = parameters.containsKey("sortBy");
        boolean hasQuery = parameters.containsKey("q");
        boolean hasFilter = filter.size()>0;

        System.out.println("ggggggg " + subcategoryName + " " + hasQuery);

        if (hasFilter){
            System.out.println("filter");
            System.out.println(filter.size());
            spec = ProductSpecification.hasAttributes(filter);
        }
        if (hasSorting){
            String sortBy = parameters.get("sortBy")[0];
            String order = hasOrder ? parameters.get("order")[0] : "";
            System.out.println("sorting");
           sort = ProductSpecification.sort(sortBy,order);
        }
        
//        if (filter.size()>0){
//            spec = spec.and(ProductSpecification.hasAttributes(filter));
//        }
        
        if (hasQuery){
            String query = parameters.get("q")[0];
            System.out.println("query");
            System.out.println("bbbbbbbbbbbb");
            spec = spec.and(ProductSpecification.hasQueryy(query));

//            customProductRepository.filterProduct(query).forEach(System.out::println);

        } else {
            System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnn" + subcategoryName);
           spec = spec.and(ProductSpecification.hasSubcategory(subcategoryName));
        }
        
        int page = Integer.parseInt(parameters.get("page")[0]);
        Pageable pageable = PageRequest.of(page-1,10,sort);

        System.out.println("zzzzzz");
        Page<Product> productPage = productRepository.findAll(spec,pageable);
        System.out.println("zzzzzz");
        productPage.getContent().forEach(System.out::println);

        return productPage
                .map(productToListViewDtoMapper);
    }




    public Page<ProductDto> getProductPagesByQuery(int pageNumber, int pageSize, Long subcategoryId, String query) {
        // -1 because pagination counts from 0
        Pageable productPages = PageRequest.of(pageNumber - 1, pageSize, Sort.by(Sort.Direction.ASC, "price"));
        Page<Product> productPage = productRepository.findAllBySubcategoryIdAndQuery(productPages, query, subcategoryId);
        return productPage
                .map(p -> new ProductDto.Builder()
                        .setId(p.getId())
                        .setCategory(p.getCategory().getCategoryName())
                        .setCreationDate(p.getCreationTime().toString())
                        .setFullDescription(p.getFullDescription())
                        .setPrice(p.getPrice())
                        .setDiscount(p.getDiscount().getDiscountPercent())
                        .setMainThumbnailPath(p.getMainThumbnailPath())
                        .setName(p.getName())
                        .setProductImagesPaths(p.getProductImagesPaths()
                                .stream()
                                .map(ProductImagePath::getImagePath)
                                .collect(Collectors.toList()))
                        .setShortDescription(p.getShortDescription())
                        .setProductAttributes(p.getProductAttributes()
                                .stream()
                                .collect(Collectors.toMap(ProductAttribute::getAttributeName,
                                        ProductAttribute::getAttributeValue)))
                        .setSubcategory(p.getSubcategory().getSubcategoryName())
                        .setQuantity(p.getQuantityCart())
                        .build());
    }


    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }


    public Page<ProductToListViewDto> search(String query, Pageable pageable) {
        Page<Product> pages = productRepository.getAllByName(query, pageable);
        return pages.map(productToListViewDtoMapper);
    }


}
