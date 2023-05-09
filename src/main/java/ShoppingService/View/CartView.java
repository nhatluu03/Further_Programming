package ShoppingService.View;

import ShoppingService.Model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CartView {
    public static void displayCart(Cart cart) {
        System.out.print("Products: ");
        ArrayList<String> productScript = new ArrayList<String>();
        if (!cart.getProductList().keySet().isEmpty()) {
            for (Product product : cart.getProductList().keySet()) {
                String productName = product.getName();
                int productQuantity = cart.getProductList().get(product);
                productScript.add(Integer.toString(productQuantity) + "x" + productName);
            }
            String products = String.join(", ", productScript);
            System.out.println(products);
        } else {
            System.out.println("Not found");
        }

        System.out.printf("Weight: %.2f\n", cart.getTotalWeight());

        if (cart.getCoupon() != null) {
            if (cart.getCoupon().getCoupon() instanceof PercentCoupon) {
                System.out.printf("Coupon: -%d % on product %s\n", cart.getCoupon().getCoupon().getDiscountAmount(), cart.getCoupon().getProduct().getName());
            } else if (cart.getCoupon().getCoupon() instanceof PriceCoupon) {
                System.out.printf("Coupon: -%.2f$ on product %s\n", cart.getCoupon().getCoupon().getDiscountAmount(), cart.getCoupon().getProduct().getName());
            }
        } else {
            System.out.println("Coupon: Not found");
        }

        System.out.printf("Tax: %.2f\n", cart.getTotalWeight());
        System.out.printf("Total: %.2f\n", cart.cartAmount());
    }

    public static void displayMessages(Cart cart) {
        System.out.println("Attached messages: ");
        for (CartItem item : cart.getItems().getCartItemsList()) {
            String itemName = item.getProduct().getName();
            String itemMsg = item instanceof CartItemGiftable ? item.getMsg() : "N/A";
            System.out.printf("%s: %s\n", itemName, itemMsg);
        }
    }

    public static void printReceipt(Cart cart) {
        if (cart.isFinal() == false) {
            cart.setFinal(true);
            System.out.println("\nPRINTING PURCHASE RECEIPT OF THE CURRENT CART");

            LocalDate currentDate = LocalDate.now();
            // Define the desired date format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Format the purchase date using the formatter
            String formattedDate = currentDate.format(formatter);
            System.out.printf("Date of purchase: %s\n", formattedDate);

            displayCart(cart);
            displayMessages(cart);
        } else {
            System.out.println("The receipt of this cart had been already printed!");
        }
    }
}
