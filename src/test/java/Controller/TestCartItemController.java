package Controller;

import ShoppingService.Controller.CartItemController;
import ShoppingService.Model.*;
import ShoppingService.View.CartView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestCartItemController {
    private CartItemController cartItemController;

    @BeforeEach
    void setUp() {
        cartItemController = new CartItemController();
    }

    @Test
    void addCartItem() {
        Cart cart = new Cart();

        Product product01 = new PhysicalProduct("Sony PlayStation 04", "This is a Disc Edition", 10, 19.99, 1.0, false, "NormalTaxRate");
        Product product02 = new PhysicalProduct("Sony PlayStation 05", "This is a Disc Edition", 10, 19.99, 1.0, false, "NormalTaxRate");

        CartItem cartItem1 = new CartItemNotGiftable(product01, cart);
        CartItem cartItem2 = new CartItemNotGiftable(product02, cart);

        cartItemController.addCartItem(cartItem1);
        cartItemController.addCartItem(cartItem2);

        List<CartItem> cartItemsList = cartItemController.getCartItemsList();
        assertEquals(2, cartItemsList.size());
        assertTrue(cartItemsList.contains(cartItem1));
        assertTrue(cartItemsList.contains(cartItem2));
    }

    @Test
    void removeCartItem() {
        Cart cart = new Cart();

        Product product01 = new PhysicalProduct("Sony PlayStation 06", "This is a Disc Edition", 10, 19.99, 1.0, false, "NormalTaxRate");
        Product product02 = new PhysicalProduct("Sony PlayStation 07", "This is a Disc Edition", 10, 19.99, 1.0, false, "NormalTaxRate");

        CartItem cartItem1 = new CartItemNotGiftable(product01, cart);
        CartItem cartItem2 = new CartItemNotGiftable(product02, cart);

        cartItemController.removeCartItem(cartItem1);
        cartItemController.removeCartItem(cartItem2);

        List<CartItem> cartItemsList = cartItemController.getCartItemsList();
        assertEquals(0, cartItemsList.size());
        assertFalse(cartItemsList.contains(cartItem1));
        assertFalse(cartItemsList.contains(cartItem2));
    }

    @Test
    void removeProduct() {
        Cart cart = new Cart();
        Product product01 = new PhysicalProduct("Sony PlayStation 08", "This is a Disc Edition", 10, 19.99, 1.0, false, "NormalTaxRate");
        Product product02 = new PhysicalProduct("Sony PlayStation 09", "This is a Disc Edition", 10, 19.99, 1.0, false, "NormalTaxRate");

        CartItem cartItem1 = new CartItemNotGiftable(product01, cart);
        CartItem cartItem2 = new CartItemNotGiftable(product02, cart);

        cartItemController.addCartItem(cartItem1);
        cartItemController.addCartItem(cartItem2);
        cartItemController.removeProduct(product02);

        List<CartItem> cartItemsList = cartItemController.getCartItemsList();
        assertEquals(1, cartItemsList.size());
        assertTrue(cartItemsList.contains(cartItem1));
        assertFalse(cartItemsList.contains(cartItem2));
    }

    @Test
    void removeMessage() {
        Cart cart = new Cart();
        Product product01 = new PhysicalProduct("Sony PlayStation 10", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");

        CartItem cartItem1 = new CartItemGiftable(product01, cart);
        cartItem1.setMsg("Happy birthday!");
        cartItemController.addCartItem(cartItem1);

        cartItemController.removeMessage(product01);

        List<CartItem> cartItemsList = cartItemController.getCartItemsList();
        assertNull(cartItem1.getMsg());
    }

    @Test
    void editMessage() {
        Cart cart = new Cart();
        Product product01 = new PhysicalProduct("Sony PlayStation 11", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        CartItem cartItem1 = new CartItemGiftable(product01, cart);
        cartItem1.setMsg("Happy birthday!");
        cartItemController.addCartItem(cartItem1);
        String newMessage = "This is the new message";
        cartItemController.editMessage(cartItem1, newMessage);
        assertEquals(newMessage, cartItem1.getMsg());
    }
}