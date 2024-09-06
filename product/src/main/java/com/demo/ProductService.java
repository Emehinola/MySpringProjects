package com.demo;

import java.util.*;

public class ProductService {

    ProductDB db = new ProductDB();

    public void addProduct(Product product){
        db.save(product);
    }

    public List<Product> getAllProducts(){
        return db.getProducts();
    }

    public Product getProductByName(String name){
        for(Product product : db.getProducts()){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    public List<Product> getProductsWithText(String text){

        List<Product> prods = new ArrayList<>();

        String str = text.toLowerCase();

        for(Product p : db.getProducts()){
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
