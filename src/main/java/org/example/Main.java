package org.example;
/**
 * @author <Luu Quoc Nhat - S3924942>
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load products from dataset
        String productFile = "src/products.txt";
        Map<String, Product> products = ShopManager.loadProducts(productFile);


        // Load carts from dataset
        String cartFile = "src/carts.txt";
        List<ShoppingCart> carts = ShopManager.loadCarts(cartFile);

//        for (ShoppingCart cart : carts) {
//            cart.printReceipt();
//            System.out.println();
//        }
        boolean exit = false;

        while (!exit) {
            ShopManager.displayMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Good bye. Have a great day!");
                    exit = true;
                    break;
                case 1:
                    ShopManager.viewProduct();
                    break;
                case 2:
                    ShopManager.createProduct();
                    break;
                case 3:
                    ShopManager.editProduct();
                    break;
                case 4:
                    ShopManager.addToCart();
                    break;
                case 5:
                    ShopManager.removeFromCart();
                    break;
                case 6:
                    ShopManager.updateGiftMessage();
                    break;
                case 7:
                    ShopManager.applyCouponToCart();
                    break;
                case 8:
                    ShopManager.removeCouponFromCart();
                    break;
                case 9:
                    System.out.println("\nDISPLAYING ALL SHOPPING CARTS");
                    ShopManager.displayCarts();
                    break;
                case 10:
                    // Sort all carts, or sort all products of a cart?
//                    ShopManager.sortCart();
                    break;
                case 11:
                    ShopManager.printPurchaseReceipt();
                    break;
                default:
                    System.out.println("Invalid entry. Please try again!\n");
            }
        }
    }
}