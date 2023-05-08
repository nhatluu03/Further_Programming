package ShoppingService.Model;

/**
 * @author Nguyen Nhat Minh - s3932112
 */

public class DigitalProduct extends Product {
    public DigitalProduct(String name, String description, int quantityAvailable, double price, boolean isGiftable, String taxType) {
        super(name, description, quantityAvailable, price, isGiftable, taxType);
    }

    @Override
    public String getType() {
        return "DIGITAL";
    }

    @Override
    public void updateWeight() {
    }


}