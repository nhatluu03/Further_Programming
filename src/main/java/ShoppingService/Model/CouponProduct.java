package ShoppingService.Model;

public class CouponProduct {
    private Coupon coupon;
    private Product product;
    public CouponProduct(Coupon coupon, Product product) {
        this.coupon = coupon;
        this.product = product;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double reducedPrice() {
        if (coupon instanceof PriceCoupon) {
            if (product.getPrice() >= coupon.getDiscountAmount()) {
                return product.getPrice() - coupon.getDiscountAmount();
            } else {
                return 0;
            }
        } else {
            return product.getPrice() - coupon.amountReduced(product.getPrice());
        }
    }
}
