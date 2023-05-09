package ShoppingService.Model;

import ShoppingService.View.CartView;

import java.util.*;

public class CartList {
    private static List<Cart> cartList;
    private static CartList instance = null;

    public static CartList getInstance() {
        if (instance == null) {
            instance = new CartList();
        }
        return instance;
    }

    private CartList() {
        cartList = new ArrayList<>();
    }

    public static List<Cart> getCartList() {
        return cartList;
    }

    public void displayCarts() {
        System.out.println("List of carts:");
        if (cartList.size() == 0) {
            System.out.println("There is no carts available.");
            return;
        }

        for (int i = 0; i < cartList.size(); i++) {
            System.out.println("--------------------------------");
            Cart cart = cartList.get(i);
            System.out.printf("Cart %d\n", i+1);
            CartView.displayCart(cart);
            String status = cart.isFinal() ? "Paid" : "Not paid";
            System.out.printf("Status: %s\n\n", status);
        }
        System.out.println("The carts are displayed in ascending order ");
    }

    public Cart getCart(int idx) {
        if (idx < 0 || idx > cartList.size()) {
            System.out.println("Cart not found. Please try again");
        }
        return cartList.get(idx);
    }
}