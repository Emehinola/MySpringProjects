package com.demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    Connection connection = null;

    public ProductDB(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "12345678");
        }catch (SQLException e){
            System.out.println(e.toString());
        }
    }

    public void save(Product product){
        String query = "INSERT INTO Products (name, type, place, warranty) VALUES (?,?,?,?)";

        try{
            PreparedStatement statement = connection.prepareStatement(query);

            // set values
            statement.setString(1, product.getName());
            statement.setString(2, product.getType());
            statement.setString(3, product.getPlace());
            statement.setInt(4, product.getWarranty());

            // execute statement
            statement.execute();
        }catch (SQLException e){
            System.out.println(e.toString());
            //
        }
    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();

        try{
            String query = "SELECT * FROM Products";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet result = statement.executeQuery();
            while (result.next()){
                Product product = new Product();
                product.setName(result.getString(1));
                product.setType(result.getString(2));
                product.setPlace(result.getString(3));
                product.setWarranty(result.getInt(4));

                products.add(product);
            }

        }catch (SQLException e){
            //
        }

        return products;
    }
}
