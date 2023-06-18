package com.project.springbootwebstore.service;

import com.project.springbootwebstore.entity.product.ProductAttribute;
import com.project.springbootwebstore.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductAttributeService {

    private final AttributeRepository attributeRepository;

    @Autowired
    public ProductAttributeService(AttributeRepository attributeRepository) {
        this.attributeRepository = attributeRepository;
    }


    public Map<String, List<String>> getAttributesBySubcategory(String subcategoryName){
        ///dfgh
        subcategoryName = subcategoryName.substring(0, 1).toUpperCase() + subcategoryName.substring(1);
        List<ProductAttribute> result = attributeRepository.findAllByProductSubcategorySubcategoryName(subcategoryName);
        Map<String, List<String>> distinctAttributes = result.stream()
               .collect(Collectors.groupingBy(ProductAttribute::getAttributeName,
                        Collectors.mapping(ProductAttribute::getAttributeValue, Collectors.toList())))
                .entrySet().stream().map(e->Map.entry(e.getKey(),e.getValue().stream().distinct().toList())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return distinctAttributes;

    }
}
