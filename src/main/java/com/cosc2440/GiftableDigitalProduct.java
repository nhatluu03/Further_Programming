package com.cosc2440;

/**
 * @author Nguyen Quoc An - s3938278
 */

public class GiftableDigitalProduct extends DigitalProduct{

    /**
    Constructs a new GiftableDigitalProduct object with the specified name, description, quantity available, price, and gift message.
    @param Name the name of the giftable digital product
    @param Description the description of the giftable digital product
    @param quantityAvailable the quantity of the giftable digital product available
    @param price the price of the giftable digital product
    @param giftMessage the gift message to be included with the product
    @throws Exception if the price is negative or the quantity is less than zero
    */
    public GiftableDigitalProduct(String Name, String Description, int quantityAvailable, double price, String taxType)
            throws Exception {
        super(Name, Description, quantityAvailable, price, taxType);
    }    

    // Getters and Setters
    @Override
    public String toString(){
        return String.format("GIFTABLE DIGITAL - %s", this.getName());
    }
}
