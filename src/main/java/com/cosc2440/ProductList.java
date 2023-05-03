package com.cosc2440;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductList {
    private static ArrayList<Product> productList = new ArrayList<>();
    private static HashMap<String, Product> productMap = new HashMap<>();

    //Getters and Setters
    public static Product getProductObjectByName(String productName){
        return productMap.get(productName);
    }

    public static HashMap getProductMap(){
        return productMap;
    }

    public static ArrayList<Product> getProductList(){
        return productList;
    }
    
}

