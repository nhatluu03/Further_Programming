package com.cosc2440;

/**
 * @author Nguyen Quoc An - s3938278
 */

public class PhysicalProduct extends Product{
    private double weight;

    /**
    Constructs a new PhysicalProduct object with the specified name, description, quantity available, price, and weight.
    @param Name the name of the physical product
    @param Description the description of the physical product
    @param quantityAvailable the quantity of the physical product available
    @param price the price of the physical product
    @param weight the weight of the physical product
    @throws Exception if the price is negative, the quantity is less than zero, or the weight is negative or zero
    */
    public PhysicalProduct(String Name, String Description, int quantityAvailable, double price, double weight, String taxType) throws Exception {
        super(Name, Description, quantityAvailable, price,taxType);
        this.weight = weight;
    }

    @Override
    public String toString(){
        return String.format("PHYSICAL - %s", this.getName());
    }

    // Getters and Setters
    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
