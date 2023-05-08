package ShoppingService.Controller;

import ShoppingService.Model.Coupon;
import ShoppingService.Model.Product;
import ShoppingService.Model.PercentCoupon;
import ShoppingService.Model.PriceCoupon;

import java.util.HashMap;
import java.util.Map;

public class CouponController {
    private Map<String, Coupon> coupons;
    public CouponController(Coupon coupon) {
        this.coupons = coupon.getCoupons();
    }

    public void addCoupon(Coupon coupon) {
        coupons.put(coupon.getId(), coupon);
    }
}
