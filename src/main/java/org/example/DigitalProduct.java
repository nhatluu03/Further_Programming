package org.example;

/**
 * @author <Luu Quoc Nhat - S3924942>
 */
public class DigitalProduct extends Product {
    // Constructors
    public DigitalProduct(String name, String description, int quantity, double price, String taxType, Coupon coupon) {
        super(name, description, quantity, price, taxType, coupon);
    }

    // String representation method
    @Override
    public String toString() {
        return String.format("DIGITAL - %s", super.name);
    }
}