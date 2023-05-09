package Controller;

import ShoppingService.Controller.CartController;
import ShoppingService.Controller.CartListController;
import ShoppingService.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCartListController {

    private CartListController cartListController;
    private CartList cartList;

    @BeforeEach
    void setUp() {
        cartList = CartList.getInstance();
        cartListController = new CartListController(cartList);
    }

    @Test
    void testAddCart() {
        // Create a sample cart
        Cart cart = new Cart();

        // Add the cart
        cartListController.addCart(cart);

        // Verify the cart has been added to the cart list
        assertTrue(CartList.getCartList().contains(cart));
    }

    @Test
    void testRemoveCart() {
        // Create a sample cart
        Cart cart = new Cart();

        // Add the cart to the cart list
        cartListController.addCart(cart);

        // Remove the cart
        cartListController.removeCart(cart);

        // Verify the cart has been removed from the cart list
        assertFalse(CartList.getCartList().contains(cart));
    }

    @Test
    void testSortCartList() {
        // Create sample carts
        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cart3 = new Cart();
        CartController cartController1 = new CartController(cart1);
        CartController cartController2 = new CartController(cart2);
        CartController cartController3 = new CartController(cart3);
        Product product1 = new PhysicalProduct("Sony PlayStation 1", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");
        Product product2 = new PhysicalProduct("Sony PlayStation 2", "This is a Disc Edition", 10, 19.99, 2.0, true, "NormalTaxRate");
        Product product3 = new PhysicalProduct("Sony PlayStation 3", "This is a Disc Edition", 10, 19.99, 3.0, true, "NormalTaxRate");
        cartController1.addItem(product1);
        cartController2.addItem(product2);
        cartController3.addItem(product3);

        // Add the carts in reverse order
        cartListController.addCart(cart3);
        cartListController.addCart(cart2);
        cartListController.addCart(cart1);

        // Sort the cart list
        cartListController.sortCartList();

        // Verify the cart list is sorted
        List<Cart> sortedList = CartList.getCartList();
        System.out.println(sortedList);
        assertEquals(cart1, sortedList.get(1));
        assertEquals(cart2, sortedList.get(2));
        assertEquals(cart3, sortedList.get(3));
    }

    @Test
    void testUpdateCartList() {
        // Create a sample cart
        Cart cart = new Cart();

        // Add the cart to the cart list
        cartListController.addCart(cart);

        // Update the cart
        cart.setTotalWeight(10.0);
        cartListController.updateCartList(cart);

        // Verify the cart has been updated in the cart list
        List<Cart> cartList = CartList.getCartList();
        assertEquals(10.0, cartList.get(1).getTotalWeight());
    }

    @Test
    void testUpdateWeightForCart() {
        // Create a sample cart
        Cart cart = new Cart();
        cartListController.addCart(cart);

        // Create a physical product with weight
        Product product = new PhysicalProduct("Sony PlayStation 15", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");

        // Add the product to the cart
        CartController cartController = new CartController(cart);
        cartController.addItem(product);

        assertEquals(1.0, cart.getTotalWeight());

        // Update the product weight
        ((PhysicalProduct) product).setWeight(2.0);

        // Update the weight for the cart
        cartListController.updateWeightForCart(product);

        // Verify the cart's total weight has been updated
        assertEquals(2.0, cart.getTotalWeight());
    }
}
