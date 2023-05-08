package ShoppingService.View;

import ShoppingService.Model.PhysicalProduct;
import ShoppingService.Model.Product;

public class ProductView {
    public static void displayProduct(Product product) {
        String type = product instanceof PhysicalProduct ? "Physical" : "Digital";
        String name = product.getName();
        double price = product.getPrice();
        String taxType = product.getTaxType();
        int quantity = product.getQuantityAvailable();
        String weight = product instanceof PhysicalProduct ? Double.toString(((PhysicalProduct) product).getWeight()) : "N/A";
        String giftable = product.isGiftable() ? "Yes" : "No";
        String description = product.getDescription();
        System.out.printf("%-10s%-35s%-10.2f%-20s%-10d%-10s%-10s%-80s", type, name, price, taxType, quantity, weight, giftable, description);
    }
}
