package org.example;

/**
 * @author <Luu Quoc Nhat - S3924942>
 */

public class DigitalGift extends DigitalProduct implements UsedAsGift {
    // Attributes declaration
    private String message;

    // Constructors
    public DigitalGift(String name, String description, int quantity, double price, String taxType, Coupon coupon, String message) {
        super(name, description, quantity, price, taxType, coupon);
        this.message = message;
    }

    // Getters & Setters
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
