package org.example;

/**
 * @author <Luu Quoc Nhat - S3924942>
 */
public class PhysicalProduct extends Product {
    // Attributes declaration
    protected double weight;

    // Constructors
    public PhysicalProduct(String name, String description, int quantity, double price, String taxType, Coupon coupon, double weight) {
        super(name, description, quantity, price, taxType, coupon);
        this.weight = weight;
    }

    // Getters and Setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // String representation method
    @Override
    public String toString() {
        return String.format("PHYSICAL - %s", super.name);
    }
}
