package ShoppingService.Controller;

import ShoppingService.Model.*;
import ShoppingService.Utils.Validations;
import ShoppingService.View.CartView;
import ShoppingService.View.ProductView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static ShoppingService.Model.Product.products;

public class ShopManager {
    public final static Scanner sc = new Scanner(System.in);
    public static Cart currentCart;
    private static CartList cartList = CartList.getInstance();

    public static void displayMenu() {
        System.out.println("\nWelcome to the Shopping App!");
        System.out.println("Please select an option:");
        System.out.println("1 - Create a new product");
        System.out.println("2 - Edit a product");
        System.out.println("3 - Add a coupon");

        System.out.println("4 - View details of all products");
        System.out.println("5 - View names of all products ");
        System.out.println("6 - View details of a product ");

        System.out.println("7 - Create new shopping cart");
        System.out.println("8 - Choose the cart");

        System.out.println("9 - Add product to current shopping cart");
        System.out.println("10 - Remove product from current shopping cart");
        System.out.println("11 - Update message for a giftable item in the current shopping cart");
        System.out.println("12 - Apply coupon to the current cart");

        System.out.println("13 - View all carts");
        System.out.println("14 - View details of the current cart");
        System.out.println("15 - Print purchase receipts for the current cart");
        System.out.println("16 - Quit");
    }


    public static void createNewProduct() {
        System.out.println("\nCREATING A NEW PRODUCT");
        String input;

        System.out.println("Enter the product name:");
        input = sc.nextLine();

        String name = Validations.validateName(input, sc);

        System.out.println("Enter the product description:");
        input = sc.nextLine();
        String description = Validations.validateEmpty(input, sc);

        System.out.println("Enter the product quantity available:");
        input = sc.nextLine();
        int quantityAvailableInt = Validations.validateNumber(input, sc);

        System.out.println("Enter the product price:");
        input = sc.nextLine();
        double price = Validations.validatePrice(input, sc);

        System.out.println("Enter the product tax type: \n\t - Free Tax Rate (1) \n\t - Normal Tax Rate (2) \n\t - Luxury Tax Rate (3)");
        input = sc.nextLine();
        String taxType = Validations.validateTaxType(input, sc);

        System.out.println("Can the product be used as a gift or not? y/n");
        input = sc.nextLine();
        boolean isGiftable = Validations.validateBoolean(input, sc);

        System.out.println("Is the product digital or physical? (d/p)");
        String type = sc.nextLine();

        Product product;
        while (true) {
            if (type.equals("d")) {
                product = new DigitalProduct(name, description, quantityAvailableInt, price, isGiftable, taxType);
                break;
            } else if (type.equals("p")) {
                System.out.println("Enter the product weight:");
                double weight = sc.nextDouble();
                sc.nextLine();
                product = new PhysicalProduct(name, description, quantityAvailableInt, price, weight, isGiftable, taxType);
                break;
            } else {
                System.out.println("Invalid product type selected!");
                System.out.println("Is the product digital or physical? (d/p)");
                type = sc.nextLine();
            }
        }
        System.out.println("Product created! Do you want to create coupon for the product? y/n");
        input = sc.nextLine();
        if (Validations.validateBoolean(input, sc)) {
            Product.getProduct(name).addCoupon(sc);
        }

        pauseToRead();
    }

    public static void editProduct() {
        System.out.println("\nEDITING A PRODUCT");
        System.out.println("Enter the name of the product you want to edit:");
        String name = sc.nextLine();

        if (Product.checkIfNameExisted(name)) {
            CartListController cartListController = new CartListController(cartList);
            Product product = Product.getProduct(name);
            ProductController productController = new ProductController(product);
            productController.updateProduct();
            System.out.println("Product edited!");
            cartListController.updateWeightForCart(Product.getProduct(name));
        } else {
            System.out.println("Product not found!");
        }
    }

    public static void createCoupon() {
        System.out.println("\nCREATING A NEW COUPON");
        System.out.println("Enter the product you want to create coupon!");
        String productName = sc.nextLine();
        if (Product.checkIfNameExisted(productName)) {
            Product product = Product.getProduct(productName);
            ProductController productController = new ProductController(product);
            productController.addCoupon(sc);
        } else {
            System.out.println("Product not found!");
        }

    }

    public static void createNewCart() {
        System.out.println("\nCREATING A NEW CART");
        currentCart = new Cart();
        CartController cartController = new CartController(currentCart);
        CartListController cartListController = new CartListController(cartList);
        cartListController.addCart(currentCart);
        cartListController.sortCartList();
        System.out.println("New shopping cart created!");
    }

    public static void addProductToCart() {
        System.out.println("\nADDING PRODUCT TO CART");
        System.out.println("Enter the name of the product you want to add to the cart:");
        String name = sc.nextLine();

        Product product = Product.getProduct(name);

        if (!Product.checkIfNameExisted(name)) {
            System.out.println("Product not found!");
        } else {
            System.out.println("Enter the amount:");
            String amount = sc.nextLine();
            int amountInt = Validations.validateNumber(amount, sc);
            CartListController cartListController = new CartListController(cartList);
            CartController cartController = new CartController(currentCart);
            cartController.addItem(product, amountInt, sc);
            cartListController.updateCartList(currentCart);
        }
    }

    public static void removeProductFromCart() {
        System.out.println("\nREMOVING PRODUCT FROM CART");
        String input;
        System.out.println("Do you want to remove an item in cart (1), or remove all product items of a product (2)?");
        input = sc.nextLine();
        String removeType = Validations.validateOption(input, sc);
        if (removeType.equals("1")) {
            CartController cartController = new CartController(currentCart);
            currentCart.showItemList();
            System.out.println("Enter the index of the item you want to remove");
            input = sc.nextLine();
            int index = Validations.validateIndex(input, currentCart.getItems().getCartItemsList().size(), sc);
            cartController.removeItem(currentCart.getItems().getItem(index-1));
        } else {
            currentCart.showProductList();
            System.out.println("Enter the product name you want to remove");
            String name = sc.nextLine();
            Product product = Product.getProduct(name);
            if (!Product.checkIfNameExisted(name)) {
                System.out.println("Product not found!");
            } else {
                currentCart.getItems().removeProduct(product);
                CartListController cartListController = new CartListController(cartList);
                cartListController.updateCartList(currentCart);

            }
        }
    }

    public static void applyCouponToCart() {
        chooseCart();
        CartController cartController = new CartController(currentCart);
        cartController.applyCoupon(sc);
    }

    public static void displayProductDetails() {
        System.out.println("\nVIEWING DETAILS OF A PRODUCT");
        System.out.println("Enter the name of the product to be displayed:");
        String name = sc.nextLine();

        if (Product.checkIfNameExisted(name)) {
            System.out.printf("%-10s%-35s%-10s%-20s%-10s%-10s%-10s%-80s\n", "Type", "Name", "Price ($)", "Tax type", "Quantity", "Weight (Kg)", "Giftable", "Description");
            Product product = Product.getProduct(name);
            ProductView.displayProduct(product);
        } else {
            System.out.println("Product not found!");
        }
    }


    public static void displayProducts() {
        System.out.println("List of products:");
        if (products.size() == 0) {
            System.out.println("There is no products available.");
            return;
        }

        System.out.printf("%-10s%-35s%-10s%-20s%-10s%-10s%-10s%-80s\n", "Type", "Name", "Price ($)", "Tax type", "Quantity", "Weight (Kg)", "Giftable", "Description");
        for (Product product : products.values()) {
            ProductView.displayProduct(product);
        }
        System.out.println();
    }

    public static void updateProductMessageInCart() {
        System.out.println("\nUPDATING MESSAGES OF ITEMS IN CART");
        System.out.println("This is all items with message in your cart:");

        CartItemList updateCartList = currentCart.getItemsWithMessage();
        updateCartList.displayCartItems();
        System.out.println("Enter the index of the item you want to change message. If not, just press enter");
        String input = sc.nextLine();
        int value;
        if (!input.isEmpty()) {
            value = Validations.validateIndex(input, updateCartList.getCartItemsList().size(), sc);
            System.out.println("Enter the new message:");
            String newMsg = sc.nextLine();
            currentCart.getItems().editMessage(updateCartList.getItem(value - 1), newMsg);
            System.out.println("MESSAGE UPDATED!");

        }
    }

    public static void chooseCart() {
        System.out.println("\nCHOOSING CURRENT CART");
        System.out.println("This is the list of carts:");
        cartList.displayCarts();
        // viet function print cart list
        System.out.println("Enter the index of the cart you want to choose:");
        String input = sc.nextLine();
        int index = Validations.validateNumber(input, sc);
        if (index > cartList.getCartList().size()) {
            System.out.println("Invalid index!");
        } else {
            currentCart = cartList.getCart(index - 1);
        }
    }

    public static void pauseToRead() {
        System.out.println("Press Enter to go back to main menu!");
        sc.nextLine();
    }

    public static void displayCartDetails() {
        System.out.println("\nVIEWING DETAILS OF CURRENT CART");
        CartView.displayCart(currentCart);
        CartView.displayMessages(currentCart);
    }

    public static void loadCartsFromFile(String filePath) throws IOException {
        Files.lines(Paths.get(filePath))
                .skip(1) // skip header line
                .filter(line -> line.startsWith("Cart"))
                .forEach(line -> {
                    Cart cart = new Cart();
                    CartController cartController = new CartController(cart);
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String cartItems = parts[1];
                        if (!cartItems.equals("N/A")) {
                            String[] cartItem = cartItems.split("\\|");
                            for (String cartItemString : cartItem) {
                                if (!cartItemString.contains("_")) {
                                    cartController.addItem(Product.getProduct(cartItemString));
                                } else {
                                    String[] token = cartItemString.split("_");
                                    cartController.addItem(Product.getProduct(token[0]), token[1]);
                                }
                            }
                        }
                    }

                    if (parts.length >= 3) {
                        String coupon = parts[2];
                        if (!coupon.equals("N/A")) {
                            String[] couponItem = coupon.split("_");
                            if (couponItem.length >= 2) {
                                cart.setCoupon(new CouponProduct(Coupon.getCoupon(couponItem[0]), Product.getProduct(couponItem[1])));
                            }
                        }
                    }
                    CartListController cartListController = new CartListController(cartList);
                    cartListController.addCart(cart);
                    cartListController.sortCartList();
                    currentCart = cart;
                });
    }


    public static void loadProductsFromFile(String filePath) throws IOException {
        Files.lines(Paths.get(filePath))
                .skip(1) // skip header line
                .filter(line -> line.startsWith("Product"))
                .map(line -> {
                    String[] parts = line.split(",");
                    String productName = parts[1];
                    String productDescription = parts[2];
                    String productType = parts[3];
                    //Product,Name,Description,Type_Weight,AvailableQuantity,Price,isGiftable,TaxType,CouponList(Id_Type_Value|Id_Type_Value)

                    int availableQuantity = Integer.parseInt(parts[4]);
                    double price = Double.parseDouble(parts[5]);
                    boolean isGiftable = Boolean.parseBoolean(parts[6]);
                    String taxType = parts[7];
                    String coupons = parts[8];
                    List<Coupon> couponList = new ArrayList<>();
                    if (!coupons.equals("N/A")) {
                        String[] couponStrings = coupons.split("\\|");
                        for (String couponString : couponStrings) {
                            String[] values = couponString.split("_");
                            String id = values[0];
                            double value = Double.parseDouble(values[2]);
                            Coupon coupon = null;
                            if (values[1].equals("1")) {
                                coupon = new PriceCoupon(id, value);
                            } else if (values[1].equals("2")) {
                                coupon = new PercentCoupon(id, value);
                            } else {
                                System.out.println(couponString);
                            }
                            CouponController couponController = new CouponController(coupon);
                            couponController.addCoupon(coupon);
                            couponList.add(coupon);
                        }
                    }
                    Product product;
                    if (productType.startsWith("1")) {
                        product = new PhysicalProduct(productName, productDescription, availableQuantity, price, Double.parseDouble(productType.substring(productType.indexOf("_") + 1)), isGiftable, taxType);
                    } else {
                        product = new DigitalProduct(productName, productDescription, availableQuantity, price, isGiftable, taxType);
                    }
                    product.setCoupons((ArrayList<Coupon>) couponList);
                    return product;
                })
                .collect(Collectors.toList());

    }
}



