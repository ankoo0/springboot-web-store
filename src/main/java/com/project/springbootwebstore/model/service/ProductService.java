package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.dto.ProductDto;
import com.project.springbootwebstore.model.dto.ProductSubcategoryDto;
import com.project.springbootwebstore.model.entity.product.*;
import com.project.springbootwebstore.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

//    private static final List<String> SEARCHABLE_FIELDS = List.of("name", "shortDescription", "fullDescription") ;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSubcategoryService subcategoryService;


    public ProductDto getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow();
        return new ProductDto.Builder()
                .setId(product.getId())
                .setCategory(product.getCategory().getCategoryName())
                .setCreationDate(product.getCreationTime().toString())
                .setFullDescription(product.getFullDescription())
                .setPrice(product.getPrice())
                .setDiscount(product.getDiscount().getDiscountPercent())
                .setMainThumbnailPath(product.getMainThumbnailPath())
                .setName(product.getName())
                .setProductImagesPaths(product.getProductImagesPaths()
                        .stream()
                        .map(ProductImagePath::getImagePath)
                        .collect(Collectors.toList()))
                .setShortDescription(product.getShortDescription())
                .setProductAttributes(product.getProductAttributes()
                        .stream()
                        .collect(Collectors.toMap(ProductAttribute::getAttributeName,
                                ProductAttribute::getAttributeValue)))
                .setSubcategory(product.getSubcategory().getSubcategoryName())
                .setQuantity(product.getQuantityCart())
                .build();
    }

//    public List<Product> getBySubcategoryId(Long subcategoryId){
//       return productRepository.findAllBySubcategoryId(subcategoryId);
//    }

//    public List<Product> getBySubcategory(String subcategoryName){
//        return productRepository.findAllBySubcategory(subcategoryName);
//    }

    public  Map<Long, ProductSubcategoryDto> countProductOcurrencies(String query){
        Map<Long, ProductSubcategoryDto> ocurrencies = new HashMap<>();
        List<ProductSubcategory> subcategories = subcategoryService.getAllSubcategories();
        List<ProductSubcategoryDto> subcategoryDtos = subcategories.stream().map(s->
                new ProductSubcategoryDto.Builder()
                        .setId(s.getId())
                        .setCategory(s.getProductCategory().getCategoryName())
                        .setName(s.getSubcategoryName())
                        .build()
        ).collect(Collectors.toList());

        for (var subcategory: subcategoryDtos) {
            Long count = productRepository.countAllBySubcategory(query,subcategory.getId());
            ocurrencies.put(count,subcategory);
        }

        return ocurrencies.entrySet().stream()
                .filter(e->e.getKey()>0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Page<ProductDto> getProductPages(int pageNumber, int pageSize, Long subcategoryId){
        // -1 because pagination counts from 0
        Pageable productPages = PageRequest.of(pageNumber-1,pageSize);
        Page<Product> productPage = productRepository.findAllBySubcategoryId(productPages,subcategoryId);
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



    public Page<ProductDto> getProductPagesByQuery(int pageNumber, int pageSize, Long subcategoryId,String query){
        // -1 because pagination counts from 0
        Pageable productPages = PageRequest.of(pageNumber-1,pageSize);
        Page<Product> productPage = productRepository.findAllBySubcategoryIdAndQuery(productPages,query,subcategoryId);
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


    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }


    public Page<ProductDto> search(String query, Pageable pageable){
        Page<Product> pages = productRepository.getAllByName(query, pageable);
        return pages
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

//    public List<Product> searchProducts(String text, List<String> fields, int limit) {
//        System.out.println("text");
//        System.out.println(fields);
//        System.out.println(limit);
//        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;
//
//        boolean containsInvalidField = fieldsToSearchBy.stream(). anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));
//
//        if(containsInvalidField) {
//            throw new IllegalArgumentException();
//        }
//
//        return productRepository.searchBy(
//                text, limit, fieldsToSearchBy.toArray(new String[0]));
//    }
}
