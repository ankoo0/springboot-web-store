package com.project.springbootwebstore.service;

import com.project.springbootwebstore.dto.AttributeData;
import com.project.springbootwebstore.dto.AttributeResponse;
import com.project.springbootwebstore.entity.product.ProductAttribute;
import com.project.springbootwebstore.repository.AttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final AttributeRepository attributeRepository;

    public List<AttributeResponse> getAttributesBySubcategory(String subcategoryName){

        subcategoryName = subcategoryName.substring(0, 1).toUpperCase() + subcategoryName.substring(1);
        List<ProductAttribute> result = attributeRepository.findDistinctByProductSubcategoryName(subcategoryName);

        return result.stream()
                .map(p->new AttributeResponse(
                        p.getName(),
                        new AttributeData("", result.stream().filter(e->e.getName().equals(p.getName())).map(ProductAttribute::getValue).distinct().toList())
                        )
                ).distinct()
                .toList();

    }
}
