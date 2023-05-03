package com.cosc2440;

/**
 * @author Nguyen Quoc An - s3938278
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.xml.crypto.Data;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);    

        // Information
        System.out.println("--------------------------------");
        System.out.println("COSC2440 INDIVIDUAL PROJECT\nONLINE SHOPPING SERVICE\nInstructor: Mr. Tri Dang");
        System.out.println("STUDENT: s3938278 - Nguyen Quoc An");
        System.out.println("--------------------------------\n");

        // Sample products creation
//        ProductList.getProductList().add(new DigitalProduct("Ebook", "A digital book", 10, 9.99,"No"));
//        ProductList.getProductList().add(new PhysicalProduct("T-shirt", "A cotton T-shirt", 20, 19.99, 0.2,"Normal"));
//        ProductList.getProductList().add(new DigitalProduct("Audiobook", "A digital audiobook", 15, 14.99,"Luxury"));
//        ProductList.getProductList().add(new PhysicalProduct("Mug", "A ceramic mug", 30, 12.99, 0.5,"No"));
//        ProductList.getProductList().add(new DigitalProduct("Movie", "A digital movie", 5, 4.99,"Normal"));
//        ProductList.getProductList().add(new PhysicalProduct("Book", "A physical book", 25, 24.99, 0.8,"Luxury"));
//        ProductList.getProductList().add(new DigitalProduct("Music Album", "A digital music album", 10, 9.99,"No"));
//        ProductList.getProductList().add(new PhysicalProduct("Vinyl Record", "A vinyl record", 15, 14.99, 0.3,"Normal"));
//        ProductList.getProductList().add(new GiftablePhysicalProduct("Shirt","A cotton shirt with a cool graphic print", 25, 300, 1.5, "Luxury"));
//        ProductList.getProductList().add(new GiftableDigitalProduct("Album", "A collection of popular songs in MP3 format", 20, 100, "No"));
//        ProductList.getProductList().add(new GiftablePhysicalProduct("Journal","A hardcover journal with a pen holder", 15, 200, 1, "Normal"));
//        ProductList.getProductList().add(new GiftableDigitalProduct("Online Course", "A comprehensive course on web development", 100, 500, "Luxury"));
        DataInitialization.loadProduct();

        //Sample cart and coupon creation
        // ShoppingCartList.createShoppingCart();
        // ShoppingCartList.createShoppingCart();
        // ShoppingCartList.createShoppingCart();

        // CouponList.getCouponList().add(new PriceCoupon(100));
        // CouponList.getCouponList().add(new PriceCoupon(100));
        // CouponList.getCouponList().add(new PercentageCoupon(20));
        // CouponList.getCouponList().add(new PercentageCoupon(50));
        DataInitialization.loadCoupon();
        DataInitialization.loadShoppingCart();



        // for (ShoppingCart s: ShoppingCartList.getShoppingCartList()){
        //     if (s.getName().equals("1")){
        //         s.addCoupon(new PriceCoupon(100));
        //     }else if (s.getName().equals("2")){
        //         s.addCoupon(new PercentageCoupon(50));
        //         s.getCouponApplied().addAppliedProductList("Mug");
        //     }
        //     else{
        //         s.addCoupon(new PercentageCoupon(100));
        //     }
        // }
        

        // Main program
        while (!quit){
            System.out.println("Online Shopping Service");
            System.out.println("< Choose you options >");
            System.out.println("0. View all products");
            System.out.println("1. Create new products");
            System.out.println("2. Edit products");
            System.out.println("3. Create new shoping cart");
            System.out.println("4. Add product to shoping cart");
            System.out.println("5. Remove product from shoping cart");
            System.out.println("6. Edit gift message of a shoping cart");
            System.out.println("7. View selected shoping cart");
            System.out.println("8. View all shoping cart");
            System.out.println("9. View all shoping cart sorted by weight");
            System.out.println("10. Apply coupon to shopping cart");
            System.out.println("11. Remove coupon from shopping cart");
            System.out.println("12. View all coupons");
            System.out.println("13. Print Receipt");
            System.out.println("14. Quit");
            System.out.println("--------------------------------");
            System.out.println("(Enter the according number to proceed)");

            switch (scanner.nextLine()){
                case "0":
                System.out.println("--------------------------------");
                    Product.displayProductList();
                    System.out.println("--------------------------------");
                    break;
                case "1":
                    System.out.println("--------------------------------");
                    Product.createProduct();
                    System.out.println("--------------------------------");
                    break;
                case "2":
                    System.out.println("--------------------------------");
                    Product.editProduct();
                    System.out.println("--------------------------------");
                    break;
                case "3":
                    System.out.println("--------------------------------"); 
                    ShoppingCartList.createShoppingCart();
                    System.out.println("--------------------------------");
                    break;
                case "4":
                    System.out.println("--------------------------------");
                    ShoppingCart.addProductToCart();
                    System.out.println("--------------------------------");
                    break;
                case "5":
                    System.out.println("--------------------------------");
                    ShoppingCart.removeProductFromCart();
                    System.out.println("--------------------------------");
                    break;
                case "6":
                    System.out.println("--------------------------------");
                    ShoppingCart.editGiftMessageOfCart();
                    System.out.println("--------------------------------");
                    break;
                case "7":
                    System.out.println("--------------------------------");
                    ShoppingCartList.viewOneCart();
                    System.out.println("--------------------------------");
                    break;
                case "8":
                    System.out.println("--------------------------------");
                    ShoppingCartList.viewAllShoppingCart();
                    System.out.println("--------------------------------");
                    break;
                case "9":
                    System.out.println("--------------------------------");
                    ShoppingCartList.viewAllShoppingCartSortedByWeight();
                    System.out.println("--------------------------------");
                    break;
                case "10":
                    System.out.println("--------------------------------");
                    ShoppingCart.addCouponToCart();
                    break;
                case "11":
                    System.out.println("--------------------------------");
                    ShoppingCart.removeCouponFromCart();
                    break;
                case "12":
                    System.out.println("--------------------------------");
                    CouponList.viewAllCoupon();
                    break;
                case "13":
                    ReceiptExporter.printReceiptForCart();
                    break;
                case "14":
                    System.out.println("--------------------------------");
                    System.out.println("Goodbye, See you again.");
                    quit = true;
            }
        }
    }
}
