package ShoppingService.Model;

public class PercentCoupon extends Coupon{
    public PercentCoupon(String id, double discountAmount) {
        super(id, discountAmount);
    }

    @Override
    public double amountReduced(double price) {
        return price * (getDiscountAmount()/100);
    }

    @Override
    public String getType() {
        return "Percent Coupon";
    }

    @Override
    public String toString() {
        return String.format("Percent Coupon: %s - %.2f%%\n", super.getId(), super.getDiscountAmount());
    }
}
