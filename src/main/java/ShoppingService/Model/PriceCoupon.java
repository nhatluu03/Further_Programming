package ShoppingService.Model;

public class PriceCoupon extends Coupon {
    public PriceCoupon(String id, double discountAmount) {
        super(id, discountAmount);
    }

    @Override
    public double amountReduced(double price) {
        return getDiscountAmount();
    }

    @Override
    public String getType() {
        return "Price Coupon";
    }

    @Override
    public String toString() {
        return String.format("Percent Coupon: %s - %.2f$\n", super.getId(), super.getDiscountAmount());
    }
}
