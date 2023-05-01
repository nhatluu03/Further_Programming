package org.example;

import java.util.HashMap;

public class Coupon {
    protected String code;
    private static HashMap<String, Coupon> coupons = new HashMap<String, Coupon>();
    public Coupon(){};
    public Coupon(String code) {
        this.code = code;
        coupons.put(code, this);
    }

    public static Coupon getCoupon(String code) {
        return coupons.get(code);
    }

    public String getCode() {
        return this.code;
    }

    public static HashMap<String, Coupon> getCoupons() {
        return coupons;
    }

    public static void setCoupons(HashMap<String, Coupon> coupons) {
        Coupon.coupons = coupons;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
