package com.cosc2440;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Coupon {
    private List<Product> appliedProductList;
    private String id;
    private static int count;
    
    public Coupon(){
        count += 1;
        appliedProductList = new ArrayList<>();
        String tmp = "Coupon"+count;
        this.id = tmp;
        CouponList.getCouponMap().put(tmp,this);
    }

    public void addAppliedProductList(Product product){
        this.appliedProductList.add(product);
    }

    public void addAppliedProductList(String productName){
        this.appliedProductList.add(ProductList.getProductObjectByName(productName));
    }

    @Override
    public String toString(){
        return String.format("Coupon ID: %s",this.getId());
    }
    //Getters and Setters 

    public List<Product> getAppliedProductList() {
        return this.appliedProductList;
    }

    public void setAppliedProductList(List<Product> appliedProductList) {
        this.appliedProductList = appliedProductList;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
