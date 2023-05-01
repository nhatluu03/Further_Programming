package org.example;
/**
 * @author <Luu Quoc Nhat - S3924942>
 */

import java.util.HashMap;
import java.util.Map;

public abstract class Product {
    // Attributes declaration
    protected String name;
    protected String description;
    protected int quantity;
    protected double price;
    protected String taxType;
    protected Coupon coupon;
    // To manipulate Product instance with the key is its name
    private static Map<String, Product> products = new HashMap<String, Product>();;


    // Constructors
    public Product(String name, String description, int quantity, double price, String taxType, Coupon coupon) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        products.put(name, this);
        this.taxType = taxType;
        this.coupon = coupon;
    }

    // Getters & Setters
    public static Product getProduct(String name) {
        return products.get(name);
    }
    public static Map<String, Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
    public String getTaxType() {
        return taxType;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        switch (this.taxType) {
            case "luxury":
                price = 0.8*price;
                break;
            case "normal":
                price = 0.9*price;
                break;
            case "free":
            default:
        }
        this.price = price;
    }

    public static void setProducts(Map<String, Product> products) {
        Product.products = products;
    }


    public void setTaxType(String taxType) {
        if (taxType.equals("luxury") || taxType.equals("normal") || taxType.equals("free")) {
            this.taxType = taxType;
        }
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public double getCouponDiscount() {
        if (this.coupon instanceof PriceCoupon) {
            return ((PriceCoupon) this.coupon).getAmount();
        }
        return this.price * ((PercentCoupon) this.coupon).getPercent();
    }

    public double getCouponDiscount(int quantity) {
        return this.getCouponDiscount() * quantity;
    }

    public double getTaxAmount() {
        switch (this.taxType) {
            case "luxury":
                return this.price *0.2;
            case "normal":
                return this.price *0.1;
            case "free":
            default:
                return 0;
        }
    }

    public void decreaseQuantity() {
        quantity--;
    }
    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }
    public void increaseQuantity(int quantity) {
        this.quantity += quantity;
    }

    // This abstract method require its non-abstract subclasses to override
    public abstract String toString();
}
