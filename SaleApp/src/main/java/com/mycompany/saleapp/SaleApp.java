/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.saleapp;

import com.mycompany.pojo.Category;
import com.mycompany.pojo.Product;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class SaleApp {

    public static void main(String[] args) {
        getProductsByName("ip");
        getProductsByPrice(5000000, 10000000);
    }

    public static void getProductsByName(String name) {
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            System.out.println("\n\n--DANH SACH SAN PHAM THEO TEN--\n\n");
//            Query q = session.createQuery("From Product Where name like '%"+name+ "%'");
            Query q = session.createQuery("From Product Where name like '% :name%'").setParameter("name", name);
            List<Product> products = q.getResultList();

            products.forEach(p -> System.out.println(p.getName()));

        }
    }
    
    public static void getProductsByPrice(double from, double to) {
        try ( Session session = HibernateUtil.getFactory().openSession()) {
            System.out.println("\n\n--DANH SACH SAN PHAM THEO GIA--\n\n");
     
            Query q = session.createQuery("From Product Where price Between " + from + " And " + to);
            List<Product> products = q.getResultList();

            products.forEach(p -> System.out.printf("%s - %.1f\n", p.getName(), p.getPrice()));
        }
    }
}
