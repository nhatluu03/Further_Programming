package Controller;

import ShoppingService.Controller.CartController;
import ShoppingService.Model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class TestCartController {
    private Cart cart;
    private CartController cartController;

    @Before
    public void setUp() {
        cart = new Cart();
        cartController = new CartController(cart);
    }

    private void provideUserInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    public void testAddItemWithMessage() {
        Product product = new PhysicalProduct("Sony PlayStation 06", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        String message = "Test Message";
        assertTrue(cartController.addItem(product, message));
        assertEquals(1, cart.getItems().getCartItemsList().size());
        assertEquals(1, cart.getProductList().get(product).intValue());
    }

    @Test
    public void testAddItemWithAmount() {
        Product product = new PhysicalProduct("Sony PlayStatio 06", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        provideUserInput("2"); // Simulate user input for adjusting the amount
        assertTrue(cartController.addItem(product));
        assertEquals(1, cart.getItems().getCartItemsList().size());
        assertEquals(1, cart.getProductList().get(product).intValue());
    }


    @Test
    public void testRemoveItem() {
        // Create a product and add it to the cart
        Product product = new PhysicalProduct("Sony PlayStati 06", "This is a Disc Edition", 10, 19.99, 1.0, false, "NormalTaxRate");
        cartController.addItem(product);
        CartItem cartItem = new CartItemNotGiftable(product, cart);

        // Remove the product from the cart
        boolean removed = cartController.removeItem(cartItem);

        // Assert that the product was successfully removed
        assertTrue(removed);
        assertEquals(0, cart.getProductList().size());
    }


    @Test
    public void testChangeAmount() {
        Product product = new PhysicalProduct("Sony PlayStat 06", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        cart.getProductList().put(product, 1);

        // Simulate user input for new amount
        String input = "2\n"; // Input the desired amount
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        Scanner scanner = new Scanner(System.in);
        assertTrue(cartController.changeAmount(product, scanner));
        assertEquals(2, cart.getProductList().get(product).intValue());
    }

    @Test
    public void testApplyCoupon() {
        Product product = new PhysicalProduct("Sony PlayStation 08", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        cartController.addItem(product);
        Coupon coupon = new PriceCoupon("CP5058", 20);
        CouponProduct productCoupon = new CouponProduct(coupon, product);
        product.addCoupon(coupon);
        cart.getProductList().put(product, 1);
        cart.setCoupon(productCoupon);

        String userInput = "Sony PlayStation 08\nCP5058\n"; // Simulated user input

        Scanner scanner = new Scanner(userInput);
        assertNotNull(cartController.applyCoupon(scanner));
        assertNotNull(cart.getCoupon().getCoupon());
    }


}

