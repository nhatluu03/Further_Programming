package com.cosc2440;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.BoldAction;

public class ReceiptExporter {
    static String filePath;
    static File file;
    static LocalDate currentDate = LocalDate.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static String formattedDate = currentDate.format(formatter);
    static Scanner scanner = new Scanner(System.in);

    public static void printReceiptForCart(){
        String shoppingCartName;
        boolean cartFound = false;

        System.out.println("Enter the shopping cart name to print receipt");
        shoppingCartName = scanner.nextLine();
        for (ShoppingCart s: ShoppingCartList.getShoppingCartList()){
            if (s.getName().equals(shoppingCartName)){
                printReceipt(s);
                s.setPaid(true);
                cartFound = true;
            }
        }
        if(!cartFound){
            System.out.println("Shopping Cart not found.");
        }

    }

    public static void printReceipt(ShoppingCart s){
        System.out.println("Enter name for the receipt file (Type default for default naming)");
        String fileName = scanner.nextLine();
        if (fileName.equalsIgnoreCase("default")){
            fileName = "Cart"+s.getName()+ "_Receipt.txt";
        }else{
            fileName += ".txt";
        }
        filePath = "./src/main/java/com/cosc2440/receipt/" + fileName;
        file = new File(filePath);
        
        if (file.exists()){
            try {
                FileWriter writer = new FileWriter(file);
                String message = String.format("|--------------------------------------------------------------|\n"+
                "|                       RECEIPT                                  |\n"+
                "|                                                              |\n"+
                "| Customer Name: An Nguyen                                      |\n"+
                "| Date Of Purchase: %s                                  |\n"+
                "|                                                              |\n"+
                "|                                                               |\n"+
                "| Cart Number: %s                                                |\n"+
                "| Product:            %s                          |\n"+
                "| Gift Message:       %s                          |\n"+
                "| Total Weight:       %.1f kg                                    |\n"+
                "| --------------------------------                              |\n"+
                "| Coupon Applied:                                               |\n"+
                "| %s                                                            |\n"+
                "| --------------------------------                              |\n"+
                "| Shipping Fee:       $%.1f                                     |\n"+
                "| Subtotal:           $%.1f                                     |\n"+
                "| Tax:                $%.1f                                     |\n"+
                "| Total:              $%.1f                                     |\n"+
                "|                                                              |\n"+
                "| Payment Method: Credit Card                                    |\n"+
                "| Card Number: **** **** **** 1234                               |\n"+
                "| Authorization Code: 123456                                     |\n"+
                "|                                                              |\n"+
                "|                                                              |\n"+
                "| Thank you for shopping with us!                                |\n"+
                "|                                                              |\n"+
                "|--------------------------------------------------------------|\n",formattedDate,s.getName(),s.getShoppingCart(),s.getGiftMessageList(),s.calculateWeight(),s.getCouponApplied(),s.calculateShippingFee(),s.cartAmount(),s.getTotalTax(),s.cartAmountWithTax());
                writer.write(message); // Write text to file
                writer.close(); // Close FileWriter object
                System.out.println(message);
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }else{
            try {
                file.createNewFile();
                try {
                    FileWriter writer = new FileWriter(file);
                    String message = String.format("|--------------------------------------------------------------|\n"+
                "|                       RECEIPT                                  |\n"+
                "|                                                              |\n"+
                "| Customer Name: An Nguyen                                      |\n"+
                "| Date: 04/30/2023                                               |\n"+
                "|                                                              |\n"+
                "|                                                               |\n"+
                "| Cart Number: %s                                               |\n"+
                "| Product:            %s                          |\n"+
                "| Gift Message:       %s                          |\n"+
                "| Total Weight:       %.1f kg                                    |\n"+
                "| --------------------------------                              |\n"+
                "| Coupon Applied:                                               |\n"+
                "| %s                                                            |\n"+
                "| --------------------------------                              |\n"+
                "| Subtotal:           $%.1f                                     |\n"+
                "| Tax:                $%.1f                                     |\n"+
                "| Total:              $%.1f                                     |\n"+
                "|                                                              |\n"+
                "| Payment Method: Credit Card                                    |\n"+
                "| Card Number: **** **** **** 1234                               |\n"+
                "| Authorization Code: 123456                                     |\n"+
                "|                                                              |\n"+
                "|                                                              |\n"+
                "| Thank you for shopping with us!                                |\n"+
                "|                                                              |\n"+
                "|--------------------------------------------------------------|\n",s.getName(),s.getShoppingCart(),s.getGiftMessageList(),s.calculateWeight(),s.getCouponApplied(),s.cartAmount(),s.getTotalTax(),s.cartAmountWithTax());
                writer.write(message); // Write text to file
                writer.close(); // Close FileWriter object
                    System.out.println(message);
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
            } catch (IOException e) {
                System.out.println("File created failed");
                e.printStackTrace();
            }
        }
        
    }
}

//    String message = String.format("|--------------------------------------------------------------|\n"+
//            "|                       RECEIPT                                  |\n"+
//            "|                                                              |\n"+
//            "| Customer Name: John Smith                                      |\n"+
//            "| Date: 04/30/2023                                               |\n"+
//            "|                                                              |\n"+
//            "| Item                Quantity               Price               |\n"+
//            "| %s                                                             |\n"+
//            "| Subtotal:           $%.1f                                     |\n"+
//            "| Tax:                $%.1f                                     |\n"+
//            "| Total:              $%.1f                                     |\n"+
//            "|                                                              |\n"+
//            "| Payment Method: Credit Card                                    |\n"+
//            "| Card Number: **** **** **** 1234                               |\n"+
//            "| Authorization Code: 123456                                     |\n"+
//            "|                                                              |\n"+
//            "| Gift Message: {}                                               |\n"+
//            "| Coupon Applied: null                                           |\n"+
//            "|                                                              |\n"+
//            "| Cart number: 1                                                 |\n"+
//            "| Weight: 0.0                                                    |\n"+
//            "| Total Price Without Tax: $0.0                                  |\n"+
//            "| Product: {}                                                    |\n"+
//            "| Tax Paid: $0.0                                                 |\n"+
//            "|                                                              |\n"+
//            "| Thank you for shopping with us!                                |\n"+
//            "|                                                              |\n"+
//            "|--------------------------------------------------------------|\n",s.getShoppingCart(),s.cartAmount(),s.getTotalTax(),s.cartAmountWithTax());
