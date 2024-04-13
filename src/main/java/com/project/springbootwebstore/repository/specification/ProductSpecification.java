package com.project.springbootwebstore.repository.specification;

import com.project.springbootwebstore.entity.product.Product;
import com.project.springbootwebstore.entity.product.ProductAttribute;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class ProductSpecification {

    private ProductSpecification() {
    }

    public static Specification<Product> hasAttributes(Map<String, String[]> attributeValues) {

        return (root, query, cb) -> {

            for (Map.Entry<String,String[]> e : attributeValues.entrySet()) {
                Join<Product, ProductAttribute> attribute1 = root.join("productAttributes", JoinType.INNER);
                Predicate p1 = cb.equal(attribute1.get("name"),e.getKey());
                List<Predicate> or = Arrays.stream(e.getValue()).map(v -> cb.equal(attribute1.get("value"), v)).toList();
                attribute1.on(cb.and(p1),cb.or(or.toArray(new Predicate[0])));
            }

            return cb.and();
        };
    }

    public static Specification<Product> hasSubcategory(String subcategoryName) {

        return (root, query, cb) -> {

            return cb.equal(cb.lower(root.get("subcategory").get("subcategoryName")), subcategoryName.toLowerCase());
        };
    }

    public static Specification<Product> hasQuery(String q) {

        return (root, query, cb) -> {

            Expression<Double> match = cb.function("match", Double.class, root.get("name"),
                    cb.parameter(String.class, q));
            return cb.and();
        };
    }

    public static Sort sort(String sorting, String order) {
        if (sorting == null && order.isEmpty()) {
            return Sort.unsorted();
        } else if (order.isEmpty()){
            return Sort.by(sorting);
        } else {
            return Sort.by(order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sorting);
        }
    }

    public static Specification<Product> hasQueryy(String q) {

        return (root, query, cb) -> {

            return cb.isTrue(
                    cb.function(
                            "document @@ plainto_tsquery",
                            Boolean.class,
                            cb.literal(q)
                    )
            );
        };
    }

}