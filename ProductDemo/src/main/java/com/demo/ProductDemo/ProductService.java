package com.demo.ProductDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    ProductDB db;

    public void addProduct(Product product){
        db.save(product);
    }

    public List<Product> getAllProducts(){
        return db.findAll();
    }

    public Product getProductByName(String name){
        return db.findByName(name);
    }

//    public Product getProductById(int id){
//        return db.findById(id);
//    }

    public List<Product> getProductsWithText(String text){

        List<Product> prods = new ArrayList<>();

        String str = text.toLowerCase();

        for(Product p : db.findAll()){
            String name = p.getName().toLowerCase();
            String type = p.getType().toLowerCase();
            String place = p.getPlace().toLowerCase();

            if(name.contains(str) || type.contains(str) || place.contains(str)){
                prods.add(p);
            }
        }

        return prods;
    }
}
