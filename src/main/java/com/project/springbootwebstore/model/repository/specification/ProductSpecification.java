package com.project.springbootwebstore.model.repository.specification;

import com.project.springbootwebstore.model.entity.product.Product;
import com.project.springbootwebstore.model.entity.product.ProductAttribute;
import com.project.springbootwebstore.model.entity.product.ProductSubcategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

public class ProductSpecification {

    // select all products with corresponding attribute names then select by values .where can contain varargs
// attribute values may overlap so add name t
//    public static Specification<Product> hasAttributesAndSubcategory(List<String> attributeValues, String subcategoryName) {
//
//        return (root, query, criteriaBuilder) -> {
//
////            query = criteriaBuilder.createQuery(Product.class);
////            root = query.from(Product.class);
//            Join<Product, ProductAttribute> attribute = root.join("productAttributes");
//            Join<Product, ProductSubcategory> subcategory = root.join("subcategory");
//            List<Predicate> filterParams = new ArrayList<>();
//            for (String val : attributeValues) {
//                filterParams.add(criteriaBuilder.equal(attribute.get("attributeValue"), val));
//            }
//
//            filterParams.add(criteriaBuilder.equal(subcategory.get("subcategoryName"), subcategoryName));
//            return criteriaBuilder.and(filterParams.toArray(new Predicate[]{}));
//        };
//    }


//    public static Specification<Product> hasAttributesAndSubcategory(Map<String, String[]> attributeValues, String subcategoryName) {
//
//        return (root, query, cb) -> {
//
//            Join<Product, ProductAttribute> attribute1 = root.join("productAttributes", JoinType.INNER);
//            Predicate p1 = cb.equal(attribute1.get("attributeName"),"Genre");
//            Predicate p2 = cb.equal(attribute1.get("attributeValue"),"Arcade");
//            Predicate p3 = cb.equal(attribute1.get("attributeValue"),"Strategy");
//            attribute1.on(cb.and(p1),cb.or(p2,p3));
//
//
//            Join<Product, ProductAttribute> attribute2 = root.join("productAttributes");
//            Predicate p11 = cb.equal(attribute2.get("attributeName"),"Year");
//            Predicate p12 = cb.equal(attribute2.get("attributeValue"),"2020");
//            Predicate p13 = cb.equal(attribute2.get("attributeValue"),"2022");
//            attribute2.on(cb.and(p11),cb.or(p12,p13));
//
//
//
////            Join<Product, ProductSubcategory> subcategory = root.join("subcategory");
////            List<Predicate> filterParams = new ArrayList<>();
////            for (String val : attributeValues) {
////                filterParams.add(cb.equal(attribute1.get("attributeValue"), val));
////            }
//
////            filterParams.add(cb.equal(subcategory.get("subcategoryName"), subcategoryName));
//            return cb.and();
//        };
//    }


    public static Specification<Product> hasAttributes(Map<String, String[]> attributeValues) {

        return (root, query, cb) -> {

            attributeValues.entrySet().forEach(e-> System.out.println(e.getKey() + "-" + e.getValue()));
            System.out.println(attributeValues.size() + " ggggggggggggggggggggggggggggggggggg");


            for (Map.Entry<String,String[]> e : attributeValues.entrySet()) {
                Join<Product, ProductAttribute> attribute1 = root.join("productAttributes", JoinType.INNER);
                Predicate p1 = cb.equal(attribute1.get("attributeName"),e.getKey());
                List<Predicate> or = Arrays.stream(e.getValue()).map(v -> cb.equal(attribute1.get("attributeValue"), v)).toList();
                attribute1.on(cb.and(p1),cb.or(or.toArray(new Predicate[0])));
            }

//            Join<Product, ProductAttribute> attribute1 = root.join("productAttributes", JoinType.INNER);
//            Predicate p1 = cb.equal(attribute1.get("attributeName"),"Genre");
//            Predicate p2 = cb.equal(attribute1.get("attributeValue"),"Arcade");
//            Predicate p3 = cb.equal(attribute1.get("attributeValue"),"Strategy");
//            attribute1.on(cb.and(p1),cb.or(p2,p3));
//
//
//            Join<Product, ProductAttribute> attribute2 = root.join("productAttributes");
//            Predicate p11 = cb.equal(attribute2.get("attributeName"),"Year");
//            Predicate p12 = cb.equal(attribute2.get("attributeValue"),"2020");
//            Predicate p13 = cb.equal(attribute2.get("attributeValue"),"2022");
//            attribute2.on(cb.and(p11),cb.or(p12,p13));



//            Join<Product, ProductSubcategory> subcategory = root.join("subcategory");
//            List<Predicate> filterParams = new ArrayList<>();
//            for (String val : attributeValues) {
//                filterParams.add(cb.equal(attribute1.get("attributeValue"), val));
//            }

//            filterParams.add(cb.equal(subcategory.get("subcategoryName"), subcategoryName));
            return cb.and();
        };
    }


    public static Specification<Product> hasSubcategory(String subcategoryName) {

        return (root, query, cb) -> {

//            Join<Product, ProductSubcategory> subcategory = root.join("subcategory");
            return cb.equal(cb.lower(root.get("subcategory").get("subcategoryName")), subcategoryName.toLowerCase());
        };
    }

//    public static Specification<Product> hasQuery(String q) {
//
//        return (root, query, cb) -> {
//            Expression<Boolean> match = cb.function("fts", Boolean.class,
//                    cb.parameter(String.class, q));
//
//            return query.where(match).getRestriction();
//        };
//    }

    public static Specification<Product> hasQuery(String q) {

        return (root, query, cb) -> {
//            Expression<Boolean> match = cb.function("custom_textsearch", Boolean.class,
//                    cb.parameter(String.class, q));

            Expression<Double> match = cb.function("match", Double.class, root.get("name"),
                    cb.parameter(String.class, q));
            return cb.and();
        };
    }

    public static Sort sort(String sorting, String order) {
        if (sorting == null && order.equals("")) {
            return Sort.unsorted();
        } else if (order.equals("")){
            return Sort.by(sorting);
        } else {
            return Sort.by(order.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sorting);
        }
    }


    public static Specification<Product> hasQueryy(String q) {

        return (root, query, cb) -> {
//            Expression<Boolean> match = cb.function("custom_textsearch", Boolean.class,
//                    cb.parameter(String.class, q));

//            Expression<Double> match = cb.function("match", Double.class, root.get("name"),
//                    cb.parameter(String.class, q));
            return cb.isTrue(
                    cb.function(
                            "document @@ plainto_tsquery",
                            Boolean.class,
                            cb.literal(q)
                    )
            );
        };
    }





//    public static Specification<Product> hasQuery(String q) {
//
//        return (root, query, cb) -> {
//            Join<Product, ProductSubcategory> subcategory = root.join("subcategory");
//            return cb.equal(subcategory.get("subcategoryName"), subcategoryName);
//        };
//    }


}