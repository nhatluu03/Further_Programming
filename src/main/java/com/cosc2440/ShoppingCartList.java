package com.cosc2440;

/**
 * @author Nguyen Quoc An - s3938278
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingCartList {
    private static int cartCount = 0;
    private static ArrayList<ShoppingCart> shoppingCartList = new ArrayList();
    private static HashMap<String,ShoppingCart> shoppingCartMap = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    /**
    Creates a new shopping cart and adds it to the shoppingCartList.
    Increments the cartCount and prints a success message.
    */
    public static void createShoppingCart(){
        shoppingCartList.add(new ShoppingCart());
        cartCount++;
        System.out.println("Shopping Cart created successfully!");
    }

    public static void viewOneCart(){
        if (ShoppingCartList.getShoppingCartList().size()==0){
            System.out.println("There are no shopping cart yet.");
            return;
        }
        System.out.println("Enter name of shopping cart to view");
        String cartName = scanner.nextLine();
        // ShoppingCart s = getShoppingCartObjectByName(cartName);
        for (ShoppingCart s: ShoppingCartList.getShoppingCartList()){
            if (s.getName().equalsIgnoreCase(cartName)){
                System.out.println(s.toString());
                return;
            }
        }
        System.out.println("Cart not exist");
    }

    /**
    Displays all the shopping carts in creation order.
    If there are no shopping carts, prints a promt message.
    */
    public static void viewAllShoppingCart(){
        if (ShoppingCartList.getShoppingCartList().size()==0){
            System.out.println("There are no shopping cart yet.");
        }
        for (ShoppingCart s: ShoppingCartList.getShoppingCartList()){
            System.out.println(s.toString());
        }
    }

    /**
    Displays all the shopping carts in ascending or descending order based on user input.
    If there are no shopping carts, prints a promt message.
    */
    public static void viewAllShoppingCartSortedByWeight(){
        Scanner scanner = new Scanner(System.in);
        boolean isAscending;
        String input;
        if (ShoppingCartList.getShoppingCartList().size()==0){
            System.out.println("There are no shopping cart yet.");
        }else{
            System.out.println("Do you want to sort in Ascending or Descending order: ");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Ascending")){
                isAscending = true;
            }else if (input.equalsIgnoreCase("Descending")){
                isAscending = false;
            }else{
                isAscending = false;
                input = "Descending";
            }
            System.out.println("Shopping cart sorted by weight ("+input+" order):\n");
            for (ShoppingCart s: ShoppingCartList.sortShoppingCartByWeight(isAscending)){
                System.out.println(s.toString());
                System.out.println();
            }
        }
    }

    /**
    Sorts the shopping carts in the shoppingCartList by their total weight.
    The method creates a clone of the shoppingCartList to avoid changing the reference of the original list.
    Uses the CartComparator class to compare the shopping carts based on their total weight.
    @param isAscending a boolean value that determines the sorting order, true for ascending order and false for descending order.
    @return an ArrayList of ShoppingCart objects sorted by their total weight.
    */
    public static ArrayList<ShoppingCart> sortShoppingCartByWeight(boolean isAscending){
        ArrayList<ShoppingCart> returnSet;
        returnSet = (ArrayList<ShoppingCart>) ShoppingCartList.getShoppingCartList().clone(); // Have to clone, otherwise it change the reference not the value
        Collections.sort(returnSet,new CartComparator(isAscending));
        return returnSet;
    }

    //Getters and Setters 
    public static ShoppingCart getShoppingCartObjectByName(String shoppingCartName){
        return shoppingCartMap.get(shoppingCartName);
    }

    public static int getCartCount(){
        return cartCount;
    }

    public static ArrayList<ShoppingCart> getShoppingCartList(){
        return shoppingCartList;
    }    

    public static HashMap<String,ShoppingCart> getShoppingCartMap(){
        return shoppingCartMap;
    }    
}
