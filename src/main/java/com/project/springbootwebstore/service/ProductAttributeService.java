package com.project.springbootwebstore.service;

import com.project.springbootwebstore.dto.AttributeData;
import com.project.springbootwebstore.dto.AttributeResponse;
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


    public List<AttributeResponse> getAttributesBySubcategory(String subcategoryName){

        subcategoryName = subcategoryName.substring(0, 1).toUpperCase() + subcategoryName.substring(1);
        List<ProductAttribute> result = attributeRepository.findDistinctByProductSubcategorySubcategoryName(subcategoryName);
        List<AttributeResponse> attributeResponses = result.stream()
                .map(p->new AttributeResponse(
                        p.getName(),
                        new AttributeData("", result.stream().filter(e->e.getName().equals(p.getName())).map(ProductAttribute::getValue).distinct().toList())
                        )
                ).distinct()
                .toList();


        attributeResponses.forEach(System.out::println);

//        Map<String, List<String>> distinctAttributes = result.stream()
//               .collect(Collectors.groupingBy(ProductAttribute::getName,
//                        Collectors.mapping(ProductAttribute::getName, Collectors.toList())));
//
//        distinctAttributes = distinctAttributes.entrySet().stream().map(e->Map.entry(e.getKey(),e.getValue().stream().distinct().toList())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return attributeResponses;

    }
}
