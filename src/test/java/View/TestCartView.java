package View;

import ShoppingService.Model.*;
import ShoppingService.View.CartView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCartView {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void displayCart() {
        // Create a sample cart with products and a coupon
        Cart cart = new Cart();
        Product product1 = new PhysicalProduct("Sony PlayStation 07", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        Product product2 = new PhysicalProduct("Sony PlayStation 08", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        cart.getProductList().put(product1, 2);
        cart.getProductList().put(product2, 1);
        Coupon coupon = new PriceCoupon("CP5050", 20);
        CouponProduct couponProduct = new CouponProduct(coupon, product1);
        cart.setCoupon(couponProduct);

        // Call the displayCart method
        CartView.displayCart(cart);

        // Normalize line separators
        String expectedOutput = "Products: 1xSony PlayStation 08, 2xSony PlayStation 07\n" +
                "Weight: 0.00\n" +
                "Coupon: -20.00$ on product Sony PlayStation 07\n" +
                "Tax: 0.00\n" +
                "Total: 20.29\n";
        expectedOutput = expectedOutput.replaceAll("\\r\\n", "\n"); // Normalize to '\n'

        String actualOutput = outContent.toString();
        actualOutput = actualOutput.replaceAll("\\r\\n", "\n"); // Normalize to '\n'

        // Verify the output
        assertEquals(expectedOutput, actualOutput);
    }




    @Test
    void displayMessages() {
        // Create a sample cart with items
        Cart cart = new Cart();
        Product product1 = new PhysicalProduct("Sony PlayStation 09", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        Product product2 = new PhysicalProduct("Sony PlayStation 10", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        CartItem item1 = new CartItemGiftable(product1, cart);
        CartItem item2 = new CartItemGiftable(product1, cart);
        item2.setMsg("Happy birthday!");
        cart.getItems().addCartItem(item1);
        cart.getItems().addCartItem(item2);

        // Call the displayMessages method
        CartView.displayMessages(cart);

        String expectedOutput = "Attached messages: \n" +
                "Sony PlayStation 09: null\n"+
                "Sony PlayStation 09: Happy birthday!\n";
        String actualOutput = outContent.toString();
        actualOutput = actualOutput.replaceAll("\\r\\n", "\n"); // Normalize to '\n'
        assertEquals(expectedOutput, actualOutput);
    }

}