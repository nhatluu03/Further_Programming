package org.example;

public class PercentCoupon extends Coupon {
    private int percent;
    public PercentCoupon(String code, int percent) {
        super(code);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        if (percent <= 99 && percent >= 1) {
            this.percent = percent;
        }
    }

    @Override
    public String toString() {
        return String.format("Percent coupon %s: %d%%", super.getCode(), this.getPercent());
    }
}
