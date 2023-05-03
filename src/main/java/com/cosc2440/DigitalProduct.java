package com.cosc2440;

/**
 * @author Nguyen Quoc An - s3938278
 */

public class DigitalProduct extends Product{

    /**
    Constructs a new DigitalProduct object with the specified name, description, quantity available, and price.
    @param Name the name of the digital product
    @param Description the description of the digital product
    @param quantityAvailable the quantity of the digital product available
    @param price the price of the digital product
    @throws Exception if the price is negative or the quantity is less than zero
    */
    public DigitalProduct(String Name, String Description, int quantityAvailable, double price, String taxType) throws Exception {
        super(Name, Description, quantityAvailable, price,taxType);
    }
    /**
    Returns a string representation of the DigitalProduct object.
    @return a string representation of the DigitalProduct object in the format "DIGITAL - [name]"
    */
    @Override
    public String toString(){
        return String.format("DIGITAL - %s", this.getName());
    }

}
