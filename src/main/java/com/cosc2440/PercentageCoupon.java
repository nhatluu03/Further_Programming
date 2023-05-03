package com.cosc2440;

public class PercentageCoupon extends Coupon{
    private int couponValue;

    public PercentageCoupon (int couponValue){
        super();
        this.couponValue = couponValue;
    }

    @Override
    public String toString(){
        return String.format("Coupon ID: %s\nCoupon Type: Percentage Coupon\nCoupon Value: %.0f%%",this.getId(),this.getCouponValue());
    }

    //Getters and Setters

    public double getCouponValue() {
        return this.couponValue;
    }

    public void setCouponValue(int couponValue) {
        this.couponValue = couponValue;
    }
}
