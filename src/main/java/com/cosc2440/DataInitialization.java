package com.cosc2440;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.naming.InitialContext;

public class DataInitialization {
    public static String productFileName = "./src/main/java/com/cosc2440/file/product.txt";
    public static String shoppingCartFileName = "./src/main/java/com/cosc2440/file/cart.txt";


    public static void loadProduct(){
        try (FileReader reader = new FileReader(productFileName)) {

            List<Product> products = Files.lines(Paths.get(productFileName))
                .filter(line -> line.startsWith("Product"))
                .map(line -> line.split(","))
                .map(token -> { //Array of String
                String type = token[1];
                switch (type){                        
                    case "Digital":
                        try {
                            return new DigitalProduct(token[2],token[3],Integer.parseInt(token[4]),Double.parseDouble(token[5]),token[6]);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    case "Physical":
                        try {
                            return new PhysicalProduct(token[2],token[3],Integer.parseInt(token[4]),Double.parseDouble(token[5]),Double.parseDouble(token[6]),token[7]);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    case "GiftableDigital":
                        try {
                            return new GiftableDigitalProduct(token[2],token[3],Integer.parseInt(token[4]),Double.parseDouble(token[5]),token[6]);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    case "GiftablePhysical":
                        try {
                            return new GiftablePhysicalProduct(token[2],token[3],Integer.parseInt(token[4]),Double.parseDouble(token[5]),Double.parseDouble(token[6]),token[7]);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    default:
                        throw new RuntimeException("Invalid product type: " + type);
                }
            })
                    .collect(Collectors.toList());

            for (Product p: products){
                ProductList.getProductList().add(p);
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void loadCoupon(){
        try (FileReader reader = new FileReader(productFileName)) {
            List<Coupon> coupons = Files.lines(Paths.get(productFileName))
                .filter(line -> line.startsWith("Coupon"))
                .map(line -> line.split(","))
                .map(token -> { //Array of String
                String type = token[0];
                switch (type){  
                    case "Coupon": 
                        if (token[1].equals("PriceCoupon")){
                            return new PriceCoupon(Double.parseDouble(token[2]));
                        }else if (token[1].equals("PercentageCoupon")){
                            return new PercentageCoupon(Integer.parseInt(token[2]));
                        }
                    default:
                        throw new RuntimeException("Invalid Coupon type: " + type);
                }
            })
                    .collect(Collectors.toList());

            for (Coupon c: coupons){
                CouponList.getCouponList().add(c);;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void loadShoppingCart() throws IOException{
        int[] count = {0};
        try (FileReader reader = new FileReader(shoppingCartFileName)){ 
            Files.lines(Paths.get(shoppingCartFileName))
            .forEach(line -> {
                String[] parts = line.split(",");

                //Cart creation and naming
                ShoppingCartList.createShoppingCart();
                count[0]++;
                String cartName = ""+count[0];
                ShoppingCart s = ShoppingCartList.getShoppingCartObjectByName(cartName);
                s.addCoupon(parts[parts.length-1]);               
                 
                for (int i=0;i < (parts.length-1);i++){
                    String[] productComponent = parts[i].split(":");
                    switch (productComponent[0]){
                        case "Digital":
                            try {
                                s.addItem(productComponent[1], Integer.parseInt(productComponent[2]));
                                break;
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        case "Physical":
                            try {
                                s.addItem(productComponent[1], Integer.parseInt(productComponent[2]));
                                break;
                            } catch (Exception e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        case "GiftableDigital":
                            for (Integer j=0; j<Integer.parseInt(productComponent[2]);j++){
                                try {
                                    s.addItem(productComponent[1], 1, productComponent[j+3]);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case "GiftablePhysical":
                            for (Integer j=0; j<Integer.parseInt(productComponent[2]);j++){
                                try {
                                    s.addItem(productComponent[1], 1, productComponent[j+3]);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            break;
                    }
                }
            });
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}



