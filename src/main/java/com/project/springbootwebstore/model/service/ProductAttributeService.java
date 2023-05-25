package com.project.springbootwebstore.model.service;

import com.project.springbootwebstore.model.entity.product.ProductAttribute;
import com.project.springbootwebstore.model.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductAttributeService {

    private final AttributeRepository attributeRepository;

    @Autowired
    public ProductAttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }


    public Map<String, List<String>> getAttributesBySubcategory(String subcategoryName){
        subcategoryName = subcategoryName.substring(0, 1).toUpperCase() + subcategoryName.substring(1);
        List<ProductAttribute> result = attributeRepository.findAllByProductSubcategorySubcategoryName(subcategoryName);
        Map<String, List<String>> distinctAttributes = result.stream()
               .collect(Collectors.groupingBy(ProductAttribute::getAttributeName,
                        Collectors.mapping(ProductAttribute::getAttributeValue, Collectors.toList())))
                .entrySet().stream().map(e->Map.entry(e.getKey(),e.getValue().stream().distinct().collect(Collectors.toList()))).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        System.out.println(distinctAttributes);
        System.out.println("-----------------------------------------------------------------------------");
        return distinctAttributes;

    }
}
