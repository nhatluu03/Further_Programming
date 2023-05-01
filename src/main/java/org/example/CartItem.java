package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class CartItem {
    private Product product;
    private ShoppingCart cart;
    private ArrayList<String> messages;
    Scanner sc = new Scanner(System.in);
    private static ArrayList<CartItem> cartItems = new ArrayList<CartItem>();

    public CartItem(Product product, ShoppingCart cart) {
        this.product = product;
        this.cart = cart;
        this.messages = new ArrayList<String>();
        this.cartItems.add(this);
    }

    public CartItem(Product product, ShoppingCart cart, ArrayList<String> messages) {
        new CartItem(product,cart);
        this.messages = messages;
    }

    public void addItem(int quantity) {
        // Get id of the last item, and increase it by 1
        int id = messages.size() + 1;

        // Specify message for giftable products
        for (int i=0; i<quantity; i++) {
            String msg = null;
            if (product instanceof DigitalProduct || product instanceof PhysicalProduct) {
                System.out.printf("Enter the gift message for an item of product %s:\n", product.getName());
                msg = sc.nextLine();
            }
            messages.add(msg);
        }
    }

    public void removeItem(ArrayList<Integer> itemIdexes) {
        for (int i : itemIdexes) {
            messages.remove(i);
        }
    }

    public void displayItems() {
        System.out.printf("Items of product %s in cart includes: \n", product);
        Product product;
        for (int i=1; i<=messages.size(); i++) {
            product = Product.getProduct(messages.get(i));
            String message;
            if (product instanceof DigitalGift) {
                message = ((DigitalGift) product).getMessage();
            } else if (product instanceof PhysicalGift) {
                message = ((PhysicalGift) product).getMessage();
            } else {
                message = "N/A";
            }
            System.out.printf("%-5d%-30s\n", i, message);
        }
    }

    public int getItemQuantity() {
        return messages.size();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }

    public static ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public static void setCartItems(ArrayList<CartItem> cartItems) {
        CartItem.cartItems = cartItems;
    }

    public static CartItem getCartItem(Product product, ShoppingCart cart) {
        for (CartItem cartItem: cartItems) {
            if (cartItem.getProduct() == product && cartItem.getCart() == cart) {
                return cartItem;
            }
        }
        return null;
    }
}
