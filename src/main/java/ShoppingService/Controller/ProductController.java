package ShoppingService.Controller;

import ShoppingService.Model.*;
import ShoppingService.Utils.Validations;

import java.util.*;

public class ProductController {

    private Product product;
    public ProductController(Product product) {
        this.product = product;
    }

    public void addCoupon(Scanner sc) {
        String input;
        System.out.println("Please input the coupon id!");
        input = sc.nextLine();
        String id = Validations.validateId(input, sc);

        System.out.println("Please choose the coupon type: Percent Coupon (1) or Price Coupon (2)");
        input = sc.nextLine();
        Coupon coupon = null;
        if (input.equals("1")) {
            System.out.println("Please input the value of the coupon: (1-99)");
            input = sc.nextLine();
            double value = Validations.validatePercentCouponValue(input, sc);
            coupon = new PercentCoupon(id, value);
        } else if (input.equals("2")) {
            System.out.println("Please input the value of the coupon: > 0 ");
            input = sc.nextLine();
            double value = Validations.validatePriceCouponValue(input, sc);
            coupon = new PriceCoupon(id, value);
        }
        product.getCoupons().add(coupon);
    }

    public void updateProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Current product information:");
        System.out.println("Name: " + product.getName());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Quantity available: " + product.getQuantityAvailable());
        System.out.println("Price: " + product.getPrice());
        System.out.println("Giftable: " + product.isGiftable());

        System.out.print("Enter new product description (or press enter to keep current description): ");
        String newDescription = sc.nextLine();
        if (!newDescription.isEmpty()) {
            product.setDescription(Validations.validateEmpty(newDescription, sc));
        }

        System.out.print("Enter new quantity available (or press enter to keep current quantity): ");
        String newQuantityStr = sc.nextLine();
        if (!newQuantityStr.isEmpty()) {
            product.setQuantityAvailable(Validations.validateNumber(newQuantityStr, sc));
        }

        System.out.print("Enter new price (or press enter to keep current price): ");
        String newPriceStr = sc.nextLine();
        if (!newPriceStr.isEmpty()) {
            product.setPrice(Validations.validatePrice(newPriceStr, sc));
        }

        product.updateWeight();
        Product.getProducts().put(product.getName(), product);

        System.out.println("Product updated successfully.");
    }

}
