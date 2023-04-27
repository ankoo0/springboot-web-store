package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductCategory;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import com.project.springbootwebstore.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final List<String> SEARCHABLE_FIELDS = List.of("name", "shortDescription", "fullDescription") ;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSubcategoryService subcategoryService;


    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

//    public List<Product> getBySubcategoryId(Long subcategoryId){
//       return productRepository.findAllBySubcategoryId(subcategoryId);
//    }

//    public List<Product> getBySubcategory(String subcategoryName){
//        return productRepository.findAllBySubcategory(subcategoryName);
//    }

    public  Map<Long,ProductSubcategory> countProductOcurrencies(String query){
        Map<Long,ProductSubcategory> ocurrencies = new HashMap<>();
        List<ProductSubcategory> subcategories = subcategoryService.getAllSubcategories();
        for (var subcategory: subcategories) {
            Long count = productRepository.countAllBySubcategory(query,subcategory.getId());
            ocurrencies.put(count,subcategory);
        }
//        return ocurrencies;
        return ocurrencies.entrySet().stream().filter(e->e.getKey()>0).collect(Collectors.toMap(e->e.getKey(),e-> e.getValue()));
    }

    public Page<Product> getProductPages(int pageNumber, int pageSize,Long subcategoryId){
        // -1 because pagination counts from 0
        Pageable productPages = PageRequest.of(pageNumber-1,pageSize);
        return productRepository.findAllBySubcategoryId(productPages,subcategoryId);
    }
    public List<Product> getAllProducts(){
        return (List<Product>) productRepository.findAll();
    }


    public Page<Product> search(String query, Pageable pageable){
        return productRepository.getAllByName(query, pageable);
    }

    public List<Product> searchProducts(String text, List<String> fields, int limit) {
        System.out.println("text");
        System.out.println(fields);
        System.out.println(limit);
        List<String> fieldsToSearchBy = fields.isEmpty() ? SEARCHABLE_FIELDS : fields;

        boolean containsInvalidField = fieldsToSearchBy.stream(). anyMatch(f -> !SEARCHABLE_FIELDS.contains(f));

        if(containsInvalidField) {
            throw new IllegalArgumentException();
        }

        return productRepository.searchBy(
                text, limit, fieldsToSearchBy.toArray(new String[0]));
    }
}
