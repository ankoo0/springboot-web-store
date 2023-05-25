package com.project.springbootwebstore.model.repository;

import com.project.springbootwebstore.model.entity.product.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomProductRepository {

    @PersistenceContext
    EntityManager em;

    public List<Product> filterProduct(String q){


        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cr = cb.createQuery(Product.class);
        Root<Product> root = cr.from(Product.class);
//        cr.select(root);
//        Expression<Boolean> match = cb.function("ftsdsfsdfsdf", Boolean.class, cb.parameter(String.class, q));
//        Predicate p = cb.conjunction();
//
//        cr.where(p);
//
//        TypedQuery<Product> query = em.createQuery(cr);
//        System.out.println("tttttttttttttttttttt");
//        List<Product> results = query.getResultList();
//        results.forEach(System.out::println);
//        System.out.println("tttttttttttttttttttt");
//        cb.and()

        //AND f1.attributeValue IN ('Arcade', 'Strategy') and function('fts', " +  q +  ") = TRUE
        String query = "select p FROM Product as p INNER JOIN p.productAttributes as f1 INNER JOIN p.productAttributes as f2 WHERE f1.attributeName = 'RAM' AND (f1.attributeValue IN ('6GB', '8GB')) and f2.attributeName = 'Year' AND (f2.attributeValue IN ('2022')) and function('fts'," +  q +  ") = TRUE";
        List<Product> p =  em.createQuery(query, Product.class).getResultList();
        System.out.println("suka");
        System.out.println("fts: ");
        //product.getProductAttributes().forEach(a-> System.out.println(a.getAttributeValue()))
        p.forEach(product -> System.out.println(product.getName()) );
//        List<String> pe =  em.createQuery("select Product.document FROM Product as p ", String.class).getResultList();



       List<Product> pe = em.createQuery("SELECT p FROM Product as p WHERE function('fts', " +  q +  ") = TRUE", Product.class).getResultList();
        System.out.println("fts: ");
//        System.out.println(pe);
        pe.forEach(product -> product.getProductAttributes().forEach(a-> System.out.println(a.getAttributeValue())));
        System.out.println("qqqqqqqqqq");
//        TypedQuery<Product> query = em.createQuery(cr);
        return Collections.emptyList();
    }


}
