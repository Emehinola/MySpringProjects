package com.demo.ProductDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/products/{name}")
    public Product getProduct(@PathVariable("name") String name){
        return service.getProductByName(name);
    }

    @PostMapping("/products")
    public String addProduct(@RequestBody Product product){
        service.addProduct(product);
        return "Done ✅";
    }
}
