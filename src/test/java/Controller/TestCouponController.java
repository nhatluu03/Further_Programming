package Controller;

import ShoppingService.Controller.CouponController;
import ShoppingService.Model.Coupon;
import ShoppingService.Model.PercentCoupon;
import ShoppingService.Model.PriceCoupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCouponController {
    private CouponController couponController;

    @BeforeEach
    void setUp() {
        Coupon coupon = new PriceCoupon("CP505", 50);
        couponController = new CouponController(coupon);
    }

    @Test
    void addCoupon() {
        // Create a sample coupon
        String couponId = "ABC123";
        double discountAmount = 10.0;
        Coupon coupon = new PriceCoupon(couponId, discountAmount);

        // Add the coupon to the controller
        couponController.addCoupon(coupon);

        // Verify that the coupon was added successfully
        assertTrue(Coupon.getCoupons().containsKey(couponId));
        assertEquals(coupon, Coupon.getCoupons().get(couponId));
    }

    @Test
    void addMultipleCoupons() {
        // Create multiple sample coupons
        String couponId1 = "ABC123";
        double discountAmount1 = 10.0;
        Coupon coupon1 = new PriceCoupon(couponId1, discountAmount1);

        String couponId2 = "DEF456";
        double discountAmount2 = 20.0;
        Coupon coupon2 = new PercentCoupon(couponId2, discountAmount2);

        // Add the coupons to the controller
        couponController.addCoupon(coupon1);
        couponController.addCoupon(coupon2);

        // Verify that the coupons were added successfully
        assertTrue(Coupon.getCoupons().containsKey(couponId1));
        assertTrue(Coupon.getCoupons().containsKey(couponId2));
        assertEquals(coupon1, Coupon.getCoupons().get(couponId1));
        assertEquals(coupon2, Coupon.getCoupons().get(couponId2));
    }

    @Test
    void getCoupon() {
        // Create a sample coupon
        String couponId = "ABC123";
        double discountAmount = 10.0;
        Coupon coupon = new PriceCoupon(couponId, discountAmount);

        // Add the coupon to the controller
        couponController.addCoupon(coupon);

        // Retrieve the coupon by its ID
        Coupon retrievedCoupon = Coupon.getCoupon(couponId);

        // Verify that the correct coupon was retrieved
        assertEquals(coupon, retrievedCoupon);
    }
}
