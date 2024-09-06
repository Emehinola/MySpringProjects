package com.demo.ProductDemo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="Products")
@Data // lombok: for reducing lines of code : it generates getter and setter for us
public class Product
{

//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(name = "product_seq", sequenceName = "Products_SEQ", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String type;
    private String place;
    private int warranty;

    public Product(String name, String type, String place,int warranty){
        this.name = name;
        this.type = type;
        this.place = place;
        this.warranty = warranty;
    }

    public Product( ) { }

    public int getId() {
        return 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    @Override
    public String toString() {
        return "Product{" +
//                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", place='" + place + '\'' +
                ", warranty=" + warranty +
                '}';
    }
}
