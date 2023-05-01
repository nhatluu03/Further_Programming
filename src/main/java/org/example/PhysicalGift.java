package org.example;

/**
 * @author <Luu Quoc Nhat - S3924942>
 */
public class PhysicalGift extends PhysicalProduct implements UsedAsGift {
    // Attributes declaration
    private String message;

    // Constructors

    public PhysicalGift(String name, String description, int quantity, double price, String taxType, Coupon coupon, double weight, String message) {
        super(name, description, quantity, price, taxType, coupon, weight);
        this.message = message;
    }

    // Methods
    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
