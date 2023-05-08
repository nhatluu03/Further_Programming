package ShoppingService.Model;

import java.util.HashMap;
import java.util.Map;

public abstract class Coupon {
    private final String id;
    private double discountAmount;
    public static Map<String, Coupon> coupons = new HashMap<>();

    public Coupon (String id, double discountAmount) {
        this.id = id;
        this.discountAmount = discountAmount;
//        coupons.put(id, this);
    }

    public static boolean checkIfCouponExisted(String id) {
        return coupons.containsKey(id);
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public abstract double amountReduced(double price);

    public static Coupon getCoupon(String id) {
        return coupons.get(id);
    }

    public String getId() {
        return id;
    }

    public static Map<String, Coupon> getCoupons() {
        return coupons;
    }

    public static void setCoupons(Map<String, Coupon> coupons) {
        Coupon.coupons = coupons;
    }

    public abstract String getType();
}
