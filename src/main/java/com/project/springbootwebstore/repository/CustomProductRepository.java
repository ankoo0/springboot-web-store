package com.project.springbootwebstore.repository;

import com.project.springbootwebstore.entity.product.Product;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
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

        String query = "select p FROM Product as p INNER JOIN p.productAttributes as f1 INNER JOIN p.productAttributes as f2 WHERE f1.attributeName = 'RAM' AND (f1.attributeValue IN ('6GB', '8GB')) and f2.attributeName = 'Year' AND (f2.attributeValue IN ('2022')) and function('fts'," +  q +  ") = TRUE";
        List<Product> p =  em.createQuery(query, Product.class).getResultList();

       List<Product> pe = em.createQuery("SELECT p FROM Product as p WHERE function('fts', " +  q +  ") = TRUE", Product.class).getResultList();

        return Collections.emptyList();
    }


}
