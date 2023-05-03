package com.cosc2440;

/**
 * @author Nguyen Quoc An - s3938278
 */

public class GiftablePhysicalProduct extends PhysicalProduct{

    /**
    Constructs a new GiftablePhysicalProduct object with the specified name, description, quantity available, price, weight, and gift message.
    @param Name the name of the giftable physical product
    @param Description the description of the giftable physical product
    @param quantityAvailable the quantity of the giftable physical product available
    @param price the price of the giftable physical product
    @param weight the weight of the giftable physical product
    @param giftMessage the gift message to be included with the product
    @throws Exception if the price is negative, the quantity is less than zero, or the weight is negative or zero
    */
    public GiftablePhysicalProduct(String Name, String Description, int quantityAvailable, double price, double weight, String taxType)
            throws Exception {
        super(Name, Description, quantityAvailable, price, weight,taxType);
    }

    // Getters and Setters
    @Override
    public String toString(){
        return String.format("GIFTABLE PHYSICAL - %s", this.getName());
    }
}
