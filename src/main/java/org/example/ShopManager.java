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

public class ShopManager {
    static Scanner sc = new Scanner(System.in);
    private static Map<String, Product> products =  Product.getProducts();
    private static List<ShoppingCart> carts =  ShoppingCart.getCarts();

    public static Map<String, Product> loadProducts(String filePath) {
        Map<String, Product> products = Product.getProducts();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            products = br.lines()
                    .map(line -> line.split(","))
                    .map(fields -> {
                        String type = fields[0];
                        boolean giftable = fields[1].equals("1") ? true : false;
                        String name = fields[2];
                        String description = fields[3];
                        int quantity = Integer.parseInt(fields[4]);
                        double price = Double.parseDouble(fields[5]);
                        String taxType = fields[6];
                        String couponType = fields[7];
                        String couponCode = fields[8];
                        Coupon coupon = null;
                        switch (couponType) {
                            case "price":
                                double couponAmount = Double.parseDouble(fields[9]);
                                coupon = new PriceCoupon(couponCode, couponAmount);
                                break;
                            case "percent":
                                int couponPercent = Integer.parseInt(fields[9]);
                                coupon = new PercentCoupon(couponCode, couponPercent);
                                break;
                        }
                        Coupon.getCoupons().put(couponCode, coupon);

                        if (type.equals("digital")) {
                            if (giftable) {
                                return new DigitalGift(name, description, quantity, price, taxType, coupon, "");
                            } else {
                                return new DigitalProduct(name, description, quantity, price, taxType, coupon);
                            }
                        } else {
                            double weight = Double.parseDouble(fields[10]);
                            if (giftable) {
                                return new PhysicalGift(name, description, quantity, price, taxType, coupon, weight, "");
                            } else {
                                return new PhysicalProduct(name, description, quantity, price, taxType, coupon, weight);
                            }
                        }
                    })
                    .collect(Collectors.toMap(Product::getName, Function.identity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<ShoppingCart> loadCarts(String filePath) {
        List<ShoppingCart> carts = ShoppingCart.getCarts();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                ShoppingCart cart = new ShoppingCart();
                String[] elements = line.split(",");
                Coupon selectedCoupon = Coupon.getCoupon(elements[0]);
                System.out.println(selectedCoupon);
                String[] cartItems = Arrays.copyOfRange(elements, 1, elements.length);
                Map<String, CartItem> productsInCart = new HashMap<String, CartItem>();
                for (String cartItem : cartItems) {
                    String[] cartItemInfo = cartItem.split("_");
                    ArrayList<String> messages = new ArrayList<String>();
                    String itemName = cartItemInfo[0];
                    int itemQuantity = Integer.parseInt(cartItemInfo[1]);
                    int count = 0;
                    for (int i=2; i<cartItemInfo.length; i++) {
                        messages.add(cartItemInfo[i]);
                    }

                    CartItem item = cart.getProducts().get(itemName);
                    Product product = products.get(itemName);
                    if (item == null) {
                        item = new CartItem(product, cart, messages);
                    }
                    productsInCart.put(itemName, item);
                    item.setMessages(messages);
                }
                cart.setProducts((HashMap<String, CartItem>) productsInCart);
                cart.setSelectedCoupon(selectedCoupon);
                carts.add(cart);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return carts;
    }

    public static void displayMenu() {
        System.out.println("\nWhat do you want:");
        System.out.println("1. View a product");
        System.out.println("2. Add a product");
        System.out.println("3. Edit a product");
        System.out.println("4. Add product items to shopping cart");
        System.out.println("5. Remove product items to shopping cart");
        System.out.println("6. Update message for gift product item");
        System.out.println("7. Apply a coupon to a cart");
        System.out.println("8. Remove a coupon from a cart");
        System.out.println("9. View details of a cart");
        System.out.println("10. Sort a cart");
        System.out.println("11. Print purchase receipt to the screen");
        System.out.println("0. Exit");
    }

    public static void createProduct() {
        System.out.println("\nADDING NEW PRODUCT");
        String name;
        do {
            System.out.println("Enter the product name: ");
            name = sc.nextLine();
            if (Product.getProducts().keySet().contains(name)) {
                System.out.println("Product with the same name already exists. Please try again!");
            }
        } while (Product.getProducts().keySet().contains(name));

        System.out.println("Enter the product description: ");
        String description = sc.nextLine();
        System.out.println("Enter the product quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the product price ($): ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Select the tax type: \n1.Tax-free\n2.Normal\n3.Luxury");
        String taxType;
        switch (sc.nextInt()) {
            case 3:
                taxType = "free";
                break;
            case 2:
                taxType = "normal";
                break;
            case 1:
            default:
                taxType = "luxury";
        }
        System.out.println("Is product digital? (y/n)");
        boolean isDigital = sc.next().charAt(0) == 'y' ? true : false;
        sc.nextLine();
        System.out.println("Select type of coupon:\n1.Price\n2.Percent");

        // Read text file, get id of the last coupon item. The id of new-generated coupon should be in format
        Coupon coupon = new Coupon();
        int input = sc.nextInt();
        while (input != 1 && input != 2) {
            System.out.println("Invalid input. Please enter either 1 or 2.");
            input = sc.nextInt();
        }

        String couponId = (UUID.randomUUID()).toString().substring(0, 10);
        if (input == 1) {
            System.out.println("Specify the amount ($) of the price coupon: ");
            double couponAmount = sc.nextDouble();
            coupon = new PriceCoupon(couponId, couponAmount);
        } else if (input == 2) {
            System.out.println("Specify the percent (%) of the percent coupon (1-99): ");
            int couponPercent = sc.nextInt();
            coupon = new PercentCoupon(couponId, couponPercent);
        }
        sc.nextLine();

        System.out.println("Can it be used as gift? (y/n)");
        // The product can be used as gift if user entry is 1, and vice versa
        boolean isGift = sc.next().charAt(0) == 'y' ? true : false;
        sc.nextLine();

        Product product;
        double weight = 0;
        if (isDigital) {
            if (isGift) {
                String message = "";
                product = new DigitalGift(name, description, quantity, price, taxType, coupon, message);
            } else {
                product = new DigitalProduct(name, description, quantity, price, taxType, coupon);
            }
        } else {
            System.out.println("Enter weight of product (kg): ");
            weight = sc.nextDouble();
            if (isGift) {
                String message = "";
                product = new PhysicalGift(name, description, quantity, price, taxType, coupon, weight, message);
            } else {
                product = new PhysicalProduct(name, description, quantity, price, taxType, coupon, weight);
            }
        }
        Product.getProducts().keySet().add(product.getName());

        System.out.println("====== Successfully created product ======");
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Quantity: " + quantity);
        System.out.println("Coupon code: " + couponId);
        System.out.println("Price: " + price);
        if (product instanceof PhysicalProduct) {
            System.out.println("Weight: " + weight + " kg");
        }
        System.out.println("Tax type: " + taxType);
        System.out.println("Is it a gift? " + (isGift ? "Yes" : "No"));
        if (isContinue("Would you like to continue to create a new one?")) {
            createProduct();
        }
    }

    public static void viewProduct() {
        System.out.println("\nVIEWING A PRODUCT");
        displayProducts();
        String name;
        Product product = null;
        do {
            System.out.println("Enter the name of product to be displayed: ");
            name = sc.nextLine();
            if (isExistProduct(name)) {
                product = Product.getProduct(name);
                break;
            } else {
                System.out.println("Product not found!");
            }
        } while (!isExistProduct(name));
        System.out.printf("%-10s%-15s%-15s%-10s%-15s%-30s\n", "Type", "Name", "Price ($)", "Giftable", "Weight (Kg)", "Description");
        String type = (product instanceof DigitalProduct) ? "Digital" : "Physical";
        double price = product.getPrice();
        String giftable = (product instanceof UsedAsGift) ? "Yes" : "No";
        String weight = (product instanceof PhysicalProduct) ? String.valueOf(((PhysicalProduct) product).getWeight()) : "N/A";
        String description = product.getDescription();
        System.out.printf("%-10s%-15s%-15.2f%-10s%-12s%-30s\n", type, name, price, giftable, weight, description);
    }
    public static void editProduct() {
        System.out.println("\nEDITING A PRODUCT");
        String name;
        Product product = null;
        displayProducts();
        do {
            System.out.println("Enter the name of product that needs editing: ");
            name = sc.nextLine();
            if (isExistProduct(name)) {
                product = Product.getProduct(name);
                break;
            } else {
                System.out.println("Product not found!");
            }
        } while (!isExistProduct(name));
        System.out.println("Select the features to edit: ");
        System.out.println("1. Description");
        System.out.println("2. Quantity");
        System.out.println("3. Price");
        System.out.println("4. Giftable");

        // for physical products only
        if (product instanceof PhysicalProduct) {
            System.out.println("5. Weight");
        }
        System.out.println("0. Exit");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 0:
                return;
            case 1:
                System.out.println("Enter the new description:");
                String newDescription = sc.nextLine();
                product.setDescription(newDescription);
                System.out.println("Successfully updated description of " + product.getName());
                break;
            case 2:
                System.out.println("Enter the new quantity:");
                int newQuantity = sc.nextInt();
                sc.nextLine();
                product.setQuantity(newQuantity);
                System.out.println("Successfully updated quantity of " + product.getName());
                break;
            case 3:
                System.out.println("Enter the new price:");
                double newPrice = sc.nextDouble();
                sc.nextLine();
                product.setPrice(newPrice);
                System.out.println("Successfully updated price of " + product.getName());
                break;
            case 4:
                System.out.println("Can product be used as gift? (y/n)");
                // The product can be used as gift if user entry is 1, and vice versa
                boolean isGift = sc.next().charAt(0) == 'y' ? true : false;
                sc.nextLine();
                System.out.println("Successfully updated giftable status of " + product.getName());
                break;
            case 5:
                if (product instanceof PhysicalProduct) {
                    System.out.println("Enter the new weight: ");
                    double newWeight = sc.nextDouble();
                    sc.nextLine();
                    // First, update the totalWeight of the carts that include that physical product
                    for (ShoppingCart cart : ShoppingCart.getCarts()) {
                        if (cart.getProducts().get(product.getName()) != null) {
                            double oldTotalWeight = cart.getTotalWeight();
                            double newTotalWeight = oldTotalWeight - ((PhysicalProduct) product).getWeight() + newWeight;
                            cart.setTotalWeight(newTotalWeight);
                        }
                    }

                    // Then, assigning updated weight to product
                    ((PhysicalProduct) product).setWeight(newWeight);
                    System.out.println("Successfully updated weight of " + product.getName());
                    break;
                }
            default:
                System.out.println("Invalid entry. Please try again!\n");
        }
        if (isContinue("Would you like to continue to edit product?")) {
            editProduct();
        }
    }

    public static void displayCoupons(ShoppingCart cart) {
        // Display all possible coupons for selection
        System.out.printf("%-10s%-15s%-15s%-15s\n", "Type", "Code", "Amount/Percent", "Total discount");
        for (String productName: cart.getProducts().keySet()) {
            Product product = Product.getProduct(productName);
            Coupon coupon = product.getCoupon();
            String code = coupon.getCode();
            CartItem cartItem = cart.getProducts().get(productName);
            int quantity = cartItem.getItemQuantity();
            String type = (coupon instanceof PriceCoupon) ? "Price" : "Percent";
            double discount = product.getCouponDiscount();
            double totalDiscount = product.getCouponDiscount(quantity);
            System.out.printf("%-10s%-15s%-15.2f%-15.2f\n", type, code, discount, totalDiscount);
        }
    }

    public static void addToCart() {
        System.out.println("\nADDING PRODUCT ITEMS TO CART");

        displayCarts();
        ShoppingCart cart = null;
        int cartIdx;
        do {
            System.out.println("Which cart to be added:");
            cartIdx = sc.nextInt();
            sc.nextLine();
            if (isExistCart(cartIdx)) {
                cart = carts.get(cartIdx);
                break;
            } else {
                System.out.println("Cart not found!");
            }
        } while (!isExistCart(cartIdx));

        displayProducts();
        String productName;
        Product product = null;
        do {
            System.out.println("Enter name of the product to be added to the current cart:");
            productName = sc.nextLine();
            if (isExistProduct(productName)) {
                product = products.get(productName);
                break;
            } else {
                System.out.println("Product not found!");
            }
        } while (!isExistProduct(productName));

        System.out.println("Specify quantity of items to be added: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        cart.addItem(product, quantity);
        System.out.printf("Successfully added %d product %s to current cart.\n", quantity, productName);
    }

    public static void removeFromCart() {
        System.out.println("\nREMOVING PRODUCT FROM CURRENT CART");

        // Specify which cart to be removed product items
        displayCarts();
        ShoppingCart cart = null;
        int cartIdx;
        do {
            System.out.println("Which cart to be removed product items:");
            cartIdx = sc.nextInt();
            sc.nextLine();
            if (isExistCart(cartIdx)) {
                cart = carts.get(cartIdx);
                break;
            } else {
                System.out.println("Cart not found!");
            }
        } while (!isExistCart(cartIdx));

        displayProducts();
        String productName;
        Product product = null;
        do {
            System.out.println("Enter name of the product to be removed to the current cart:");
            productName = sc.nextLine();
            if (isExistProduct(productName) && cart.containsProduct(productName)) {
                product = products.get(productName);
                break;
            } else {
                System.out.println("Product not found!");
            }
        } while (!isExistProduct(productName));

        System.out.println("Specify quantity of items to be added: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        if (quantity > cart.getProducts().size()) {
            cart.removeItem(product, quantity);
            System.out.printf("Successfully removed %d product %s to current cart.\n", quantity, productName);
        } else {
            System.out.println("The number of items in cart is less than specified number! Please try again.");
        }
    }


    public static void displayCartAmount() {
        System.out.println("\nDISPLAYING THE AMOUNT OF CURRENT CART");
    }

    public static void displayCarts() {
        if (carts.isEmpty()) {
            System.out.println("There is no carts available.");
            return;
        }

        Collections.sort(carts);

        System.out.printf("%-10s%-15s%-15s%-15s\n", "Order", "N.o items", "Amount ($)", "Weight (kg)");
        for (int i = 0; i < carts.size(); i++) {
            int noItems = carts.get(i).getProducts().size();
            double amount = carts.get(i).cartAmount();
            double weight = carts.get(i).getTotalWeight();
            System.out.printf("%-10d%-15d%-15.2f%-15.2f\n", i + 1, noItems, amount, weight);
        }
    }

    public static void sortCarts() {
        Collections.sort(carts);
    }

    public static void printPurchaseReceipt() {
        System.out.println("\nPRINTING PURCHASE RECEIPT");

        displayCarts();
        ShoppingCart cart = null;
        int cartIdx;
        do {
            System.out.println("Which cart to be printed:");
            cartIdx = sc.nextInt();
            sc.nextLine();
            if (isExistCart(cartIdx)) {
                cart = carts.get(cartIdx);
                break;
            } else {
                System.out.println("Cart not found!");
            }
        } while (!isExistCart(cartIdx));
        cart.printReceipt();
    }

    public static void displayProducts() {
        if (Product.getProducts().keySet().isEmpty()) {
            System.out.println("There is no products available.");
            return;
        }
        System.out.printf("%-10s%-15s%-15s%-15s%-10s%-15s%-30s\n", "Type", "Name", "Quantity", "Price ($)", "Giftable", "Weight (Kg)", "Description");
        for (String productName : Product.getProducts().keySet()) {
            Product product = Product.getProduct(productName);
            String type = (product instanceof DigitalProduct) ? "Digital" : "Physical";
            String name = product.getName();
            int quantity = product.getQuantity();
            double price = product.getPrice();
            String giftable = (product instanceof UsedAsGift) ? "Yes" : "No";
            String weight = (product instanceof PhysicalProduct) ? String.valueOf(((PhysicalProduct) product).getWeight()) : "Null";
            String description = product.getDescription();
            System.out.printf("%-10s%-15s%-15d%-15.2f%-10s%-12s%-30s\n", type, name, quantity, price, giftable, weight, description);
        }
    }

    public static void updateGiftMessage() {
        System.out.println("\nUPDATING GREETING MESSAGES FOR PRODUCT ITEMS");

        displayCarts();
        ShoppingCart cart = null;
        int cartIdx;
        do {
            System.out.println("Which cart to be added:");
            cartIdx = sc.nextInt();
            sc.nextLine();
            if (isExistCart(cartIdx)) {
                cart = carts.get(cartIdx);
                break;
            } else {
                System.out.println("Cart not found!");
            }
        } while (!isExistCart(cartIdx));

        displayProducts();
        String productName;
        Product product = null;
        do {
            System.out.println("Enter name of the product to be added to the current cart:");
            productName = sc.nextLine();
            if (isExistProduct(productName)) {
                product = products.get(productName);
                break;
            } else {
                System.out.println("Product not found!");
            }
        } while (!isExistProduct(productName));
        System.out.println("Successfully updated gift message for product items");
    }

    public static void applyCouponToCart() {
        System.out.println("\nAPPLYING COUPON TO CART");
        displayCarts();
        ShoppingCart cart = null;
        int cartIdx;
        do {
            System.out.println("Which cart to be applied coupon:");
            cartIdx = sc.nextInt();
            sc.nextLine();
            if (isExistCart(cartIdx)) {
                cart = carts.get(cartIdx);
                break;
            } else {
                System.out.println("Cart not found!");
            }
        } while (!isExistCart(cartIdx));

        displayCoupons(cart);

        String id;
        boolean isValidCouponEntry = false;
        do {
            System.out.println("Enter coupon code:");
            id = sc.nextLine();
            isValidCouponEntry = Coupon.getCoupon(id) != null || cart.isValidCouponCode(id);
            if (isValidCouponEntry) {
                Coupon coupon = Coupon.getCoupon(id);
                cart.setSelectedCoupon(coupon);
            }
        } while (isValidCouponEntry);
        System.out.println("Successfully applied coupon to cart");
    }

    public static void removeCouponFromCart() {
        System.out.println("\nREMOVING COUPON FROM CART");
        displayCarts();
        ShoppingCart cart = null;
        int cartIdx;
        do {
            System.out.println("Which cart to be removed coupon:");
            cartIdx = sc.nextInt();
            sc.nextLine();
            if (isExistCart(cartIdx)) {
                cart = carts.get(cartIdx);
                break;
            } else {
                System.out.println("Cart not found!");
            }
        } while (!isExistCart(cartIdx));

        displayCoupons(cart);
        System.out.printf("Are you sure to remove coupon %s from cart? (y/n)\n", cart.getSelectedCoupon().getCode());
        boolean isAccept = sc.next().charAt(0) == 'y' ? true : false;
        if (isAccept) {
            cart.setSelectedCoupon(null);
            System.out.println("Successfully removed coupon from cart");
        }
    }

    public static boolean isExistProduct(String productName) {
        Product product = Product.getProduct(productName);
        return product != null;
    }

    public static boolean isExistCart(int index) {
        if (index < 1 || index > carts.size()) {
            return false;
        }
        ShoppingCart cart = carts.get(index - 1);
        return cart != null;
    }

    public static boolean isContinue(String message) {
        System.out.printf("\n%s (y/n)\n", message);
        boolean isContinue = sc.next().charAt(0) == 'y' ? true : false;
        sc.nextLine();
        if (isContinue) {
            return true;
        }
        return false;
    }

}