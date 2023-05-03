package com.cosc2440;

/**
 * @author Nguyen Quoc An - s3938278
 */

import java.util.Comparator;

public class CartComparator implements Comparator<ShoppingCart>{
    private boolean isAscending;

    /**
     * Constructs a new CartComparator with the specified sorting order.
     * @param isAscending the sorting order to be used, where true indicates ascending order, and false indicates descending order
     */
    public CartComparator(boolean isAscending){
        this.isAscending = isAscending;
    }

    /**
    Compares two shopping carts based on their calculated weight.
    @param c1 the first shopping cart to be compared
    @param c2 the second shopping cart to be compared
    @return a negative integer, zero, or a positive integer as the first argument is less than,
    equal to, or greater than the second, based on whether the comparison is in ascending or descending order.
    @throws NullPointerException if either shopping cart is null
    */
    @Override
    public int compare(ShoppingCart c1, ShoppingCart c2)
     {
        if (isAscending){
            return Double.compare(c1.calculateWeight(), c2.calculateWeight());
        }else{
            return Double.compare(c2.calculateWeight(), c1.calculateWeight());
        }
    }
    
}
