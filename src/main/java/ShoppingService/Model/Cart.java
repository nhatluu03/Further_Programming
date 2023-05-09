package ShoppingService.Model;

/**
 * @author Nguyen Nhat Minh - s3932112
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Comparable<Cart> {
    // Attribute declarations
    private final CartItemList items;
    private final Map<Product, Integer> productList;
    private double totalWeight;
    private CouponProduct coupon;
    private boolean isFinal;
    private final double baseFee = 0.1;

    // Constructors
    public Cart() {
        items = new CartItemList();
        productList = new HashMap<>();
        totalWeight = 0;
        coupon = null;
        isFinal = false;
    }

    public boolean printCoupons() {
        String couponInfo = "";
        boolean value = false;

        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {

            Product product = entry.getKey();
            int amount = entry.getValue();
            if (product.getCoupons().size() != 0) {
                couponInfo += String.format("\tProduct: %s\n", product.getName());
                for (Coupon coupon : product.coupons) {
                    couponInfo += String.format("\t\t - ID: %s \t Type: %s \t Value %s \t Estimated Reduced: %s\n", coupon.getId(), coupon.getType(), coupon.getDiscountAmount(), coupon.amountReduced(product.getPrice()) * amount);
                }
                couponInfo += "\n";
            }
        }
        if (couponInfo.equals("")) {
            System.out.println("There is no available coupons");
        } else {
            System.out.println("These are the available coupons for your cart!");
            System.out.println(couponInfo);
            value = true;
        }
        return value;
    }

    public double cartAmount() {
        double totalAmount = 0;
        double totalProductPrice = 0;
        double totalProductTax = 0;


        // Calculate the total price and tax for all products in the cart
        for (Map.Entry<Product, Integer> entry : productList.entrySet()) {
            Product product = entry.getKey();
            double price = product.getPrice();
            double tax = product.getTax();
            double amount = entry.getValue();

            if (coupon != null && product.equals(coupon.getProduct())) {
                price = coupon.reducedPrice();
            }

            totalProductPrice += price * amount;

            totalProductTax += tax * amount;
        }

        totalAmount = totalProductPrice + totalProductTax;

        // Add shipping fee for physical products
        if (totalWeight > 0) {
            double shippingFee = totalWeight * baseFee;
            totalAmount += shippingFee;
        }

        return totalAmount;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public CartItemList getItems() {
        return items;
    }

    public CartItemList getItemsWithMessage() {
        CartItemList itemsWithMessage = new CartItemList();
        for (CartItem item : items
        ) {
            if (item instanceof CartItemGiftable) {
                itemsWithMessage.addCartItem(item);
            }
        }
        return itemsWithMessage;
    }

    public void showProductList() {
        System.out.println();
        System.out.println(productList);
    }

    public void showItemList() {
        System.out.println();
        items.displayCartItems();
    }

    public int getSize() {
        return items.getCartItemsList().size();
    }

    public Map<Product, Integer> getProductList() {
        return productList;
    }

    public CouponProduct getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponProduct coupon) {
        this.coupon = coupon;
    }



    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public double getBaseFee() {
        return baseFee;
    }

    @Override
    public int compareTo(Cart other) {
        return Double.compare(this.totalWeight, other.totalWeight);
    }
}

