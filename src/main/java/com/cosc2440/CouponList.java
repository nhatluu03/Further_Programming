package com.cosc2440;

import java.util.ArrayList;
import java.util.HashMap;

public class CouponList {
    private static ArrayList<Coupon> couponList = new ArrayList();
    private static HashMap<String,Coupon> couponMap = new HashMap<>();

    public static void viewAllCoupon(){
        for (Coupon c: couponList){
            System.out.println(c);
            System.out.println("--------------------------------");
        }
    }

    //Getters and Setters
    public static Coupon getCouponObjectByName(String couponName){
        return couponMap.get(couponName);
    }

    public static HashMap getCouponMap(){
        return couponMap;
    }

    public static ArrayList<Coupon> getCouponList(){
        return couponList;
    }

    
}
