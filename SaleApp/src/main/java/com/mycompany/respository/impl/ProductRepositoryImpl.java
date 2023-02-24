/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.respository.impl;

import com.mycompany.pojo.Product;
import com.mycompany.respository.ProductRepository;
import com.mycompany.saleapp.HibernateUtil;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

/**
 *
 * @author AnChuPC
 */
public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<Product> getProducts(Map<String, String> params) {
        try (Session s = HibernateUtil.getFactory().openSession()) {
            CriteriaBuilder b = s.getCriteriaBuilder();
            CriteriaQuery<Product> q = b.createQuery(Product.class);
            Root<Product> root = q.from(Product.class);
            q.select(root);

            // Filter theo key word
            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                Predicate p1 = b.like(root.get("name").as(String.class),
                        String.format("%%%s%%", kw));
                q.where(p1);
            }

            // Filter theo price
            String fromPrice = params.get("fromPrice");
            String toPrice = params.get("toPrice");

            if (fromPrice != null && !fromPrice.isEmpty()) {
                Predicate p2 = b.greaterThanOrEqualTo(root.get("price").as(Double.class),
                        Double.parseDouble(fromPrice));
                q.where(p2);
            }

            if (toPrice != null && !toPrice.isEmpty()) {
                Predicate p3 = b.lessThanOrEqualTo(root.get("price").as(Double.class), Double.parseDouble(toPrice));
                q.where(p3);
            }

            Query query = s.createQuery(q);
            return query.getResultList();
        }
    }
}
