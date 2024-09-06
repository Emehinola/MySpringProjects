package com.demo;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        ProductService service = new ProductService();
//        service.addProduct(new Product("Hp Laptop", "Computer", "Desk", 2024));
//        service.addProduct(new Product("Book", "Stationery", "Shelf", 2020));
//        service.addProduct(new Product("MacBook", "Computer", "Black Bag", 2026));
//        service.addProduct(new Product("Biro", "Stationery", "Drawer", 2023));
//        service.addProduct(new Product("AC remote", "Accessory", "Table", 2024));
//        service.addProduct(new Product("Mic", "Audio System", "Table", 2024));
//        service.addProduct(new Product("Fan", "Electronic", "Floor", 2029));
//        service.addProduct(new Product("Television", "Electronic", "Wall", 2019));
//        service.addProduct(new Product("Blue Biro", "Stationery", "Pulse", 2025));

        List<Product> products = service.getAllProducts();

        for(Product product : products){
            System.out.println(product);
        }

        System.out.println("============= GET A PRODUCT ===============");

        Product product = service.getProductByName("Biro");
//        System.out.println(product.getPlace());

        System.out.println("============= SEARCH ===============");
        System.out.println(service.getProductsWithText("stationery"));

    }
}