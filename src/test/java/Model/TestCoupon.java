package Model;

import ShoppingService.Model.*;
import org.junit.Before;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestCoupon {

    @Test
    public void testCreateCoupon() {
        Coupon percentCoupon = new PercentCoupon("PercentCoupon", 10);
        Coupon priceCoupon = new PriceCoupon("PriceCoupon", 22);
        assertEquals("Percent Coupon", percentCoupon.getType());
        assertEquals("Price Coupon", priceCoupon.getType());
        assertEquals(22, priceCoupon.getDiscountAmount());
        assertEquals(10, percentCoupon.getDiscountAmount());
    }

    @Test
    public void testAmountReduced() {
        PhysicalProduct physicalProduct = new PhysicalProduct("Book", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");

        Coupon percentCoupon = new PercentCoupon("PercentCouponAmount", 10);
        Coupon priceCoupon = new PriceCoupon("PercentCouponAmount", 4);
        assertEquals(percentCoupon.amountReduced(physicalProduct.getPrice()),1);
        assertEquals(priceCoupon.amountReduced(physicalProduct.getPrice()),4);
    }

    @Test
    public void testCouponProduct() {
        PhysicalProduct physicalProduct = new PhysicalProduct("Novel", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");

        Coupon percentCoupon = new PercentCoupon("PercentCouponAmountProduct", 10);
        Coupon priceCoupon = new PriceCoupon("PercentCouponAmountProduct", 4);
        CouponProduct coupon1 = new CouponProduct(percentCoupon, physicalProduct);
        CouponProduct coupon2 = new CouponProduct(priceCoupon, physicalProduct);
        assertEquals(coupon1.reducedPrice(),9);
        assertEquals(coupon2.reducedPrice(),6);
    }

}
