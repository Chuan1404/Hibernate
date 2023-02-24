/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.saleapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mycompany.pojo.Product;
import com.mycompany.respository.ProductRepository;
import com.mycompany.respository.impl.ProductRepositoryImpl;

/**
 *
 * @author admin
 */
public class SaleApp {

    public static void main(String[] args) {
        ProductRepository p = new ProductRepositoryImpl();
        
        Map<String, String> params = new HashMap<>();
        params.put("kw", "iphone");
        params.put("fromPrice", "10000000");
        params.put("toPrice", "20000000");
        
        List<Product> products = p.getProducts(params);
        products.forEach(x -> System.out.printf("%s - %.1f\n", x.getName(), x.getPrice()));
    }
}
