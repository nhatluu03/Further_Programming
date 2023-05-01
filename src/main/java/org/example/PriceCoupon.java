package org.example;

public class PriceCoupon extends Coupon {
    private double amount;
    public PriceCoupon(String code, double amount) {
        super(code);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Price coupon %s: %.2f$", super.getCode(), this.getAmount());
    }
}
