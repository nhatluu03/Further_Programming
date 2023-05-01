package org.example;
/**
 * @author <Luu Quoc Nhat - S3924942>
 */
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ShoppingCart implements Comparable<ShoppingCart> {
    Scanner sc = new Scanner(System.in);
    // Attributes declarations
    private double totalWeight;
    private Coupon selectedCoupon;
    private Map<String, CartItem> products;
    private static List<ShoppingCart> carts = new ArrayList<ShoppingCart>();


    // Constructors
    public ShoppingCart() {
        products = new HashMap<String, CartItem>();
        totalWeight = 0.0;
    }

    // Methods
    public boolean addItem(Product product, int quantity) {
        if (product == null || product.getQuantity() == 0 || quantity > product.getQuantity()) {
            return false;
        }
        if (product instanceof PhysicalProduct) {
            totalWeight += ((PhysicalProduct) product).getWeight() * quantity;
        }
        product.decreaseQuantity(quantity);
        CartItem cartItem = CartItem.getCartItem(product, this);
        // If product is already exist in cart, add the items of that product to cartItem
        if (cartItem == null) {
            cartItem = new CartItem(product, this);
            products.put(product.getName(), cartItem);
        } else {
            cartItem.addItem(quantity);
        }
        cartItem.addItem(quantity);
        return true;
    }
    public boolean removeItem(Product product, int quantity) {
        if (product == null || product.getQuantity() == 0 || quantity < 1) {
            return false;
        }
        if (product instanceof PhysicalProduct) {
            totalWeight -= ((PhysicalProduct) product).getWeight();
        }
        product.increaseQuantity(quantity);
        CartItem cartItem = CartItem.getCartItem(product, this);
        if (cartItem == null) {
            cartItem = new CartItem(product, this);
        }

        System.out.println("Specify index of items to be removed (seperated by whitespace):");
        ArrayList<Integer> itemIdexes = new ArrayList<>();
        int count = 0;

        while (count < quantity && sc.hasNextInt()) {
            int num = sc.nextInt();
            itemIdexes.add(num);
            count++;
        }

        cartItem.removeItem(itemIdexes);
        return true;
    }

    public boolean updateGiftMessage(Product product) {
        if (product == null || product.getQuantity() == 0) {
            return false;
        }
        if (product instanceof PhysicalProduct) {
            totalWeight -= ((PhysicalProduct) product).getWeight();
        }
        CartItem cartItem = CartItem.getCartItem(product, this);
        if (cartItem == null) {
            cartItem = new CartItem(product, this);
        }

        cartItem.displayItems();
        System.out.println("Specify index of items to be removed (seperated by whitespace):");
        ArrayList<Integer> itemIdexes = new ArrayList<>();
        int count = 0;

        while (sc.hasNextInt()) {
            int num = sc.nextInt();
            itemIdexes.add(num);
            count++;
        }

        cartItem.removeItem(itemIdexes);
        return true;
    }

    public double cartAmount() {
        double totalAmount = 0.0;
        double shippingFee = getShippingFee();

        // Final cart amount is equals to algebraic sum of ((price + tax - discount) * quantity) of all products in cart
        // Then, added by total shipping fee based on totalWeight of the cart
        // totalAmount = (getTotalPrice() + getTotalTax() - getTotalDiscount()) + getTotalShippingFee()
        double discount = 0;
        for (String productName : products.keySet()) {
            Product product = Product.getProduct(productName);
            double price = product.getPrice();
            double tax = product.getTaxAmount();
            int quantity = products.get(productName).getItemQuantity();

            if (product.getCoupon() == this.selectedCoupon) {
                discount += product.getCouponDiscount(quantity);
            }
            if (product != null) {
                totalAmount += (price + tax)* quantity;
            }
        }
        totalAmount -= discount;
        totalAmount += shippingFee;
        return totalAmount;
    }

    public double getShippingFee() {
        final double baseFee = 0.1;
        return totalWeight * baseFee;
    }

    public double cartDiscount(Coupon coupon) {
        // Calculate all discount based on the selected coupon
        for (String productName: products.keySet()) {
            Product product = Product.getProduct(productName);
            if (product.getCoupon() == coupon) {
                CartItem cartItem = CartItem.getCartItem(product, this);
                int quantity = cartItem.getItemQuantity();

                if (coupon instanceof PriceCoupon) {
                    return  ((PriceCoupon) coupon).getAmount() * quantity;
                } else if (coupon instanceof  PercentCoupon) {
                    return  ((PercentCoupon) coupon).getPercent() * product.getPrice() * quantity;
                }
            }
        }
        return 0;
    }

    public void displayProducts() {
        // Display all product in cart
        if (products == null || products.isEmpty()) {
            System.out.println("There is no products available in the cart.");
            return;
        }
        System.out.printf("%-10s%-15s%-15s%-15s%-10s%-15s%-30s%-30s\n", "Type", "Name", "Price", "Quantity", "Giftable", "Weight", "Description", "Greeting message(s)");
        for (String productName : products.keySet()) {
            Product product = Product.getProduct(productName);
            String type = (product instanceof DigitalProduct) ? "Digital" : "Physical";
            String name = product.getName();
            int quantity = products.get(name).getItemQuantity();
            double price = product.getPrice();
            String giftable;
            String greetingMessage;
            if (product instanceof UsedAsGift) {
                giftable = "Yes";
                CartItem cartItem = CartItem.getCartItem(product, this);

                // Format messages
                Collection<String> messages = cartItem.getMessages();
                greetingMessage = "[" + String.join(", ", messages) + "]";
            } else {
                giftable = "No";
                greetingMessage = "N/A";
            }
            String weight = (product instanceof PhysicalProduct) ? String.valueOf(((PhysicalProduct) product).getWeight()) + " kg" : "N/A";
            String description = product.getDescription();
            System.out.printf("%-10s%-15s%-15.2f%-15d%-10s%-12s%-30s%-30s\n", type, name, price, quantity, giftable, weight, description, greetingMessage);
        }
    }

    public int compareTo(ShoppingCart other) {
        if (this.totalWeight < other.totalWeight) {
            return -1;
        } else if (this.totalWeight > other.totalWeight) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isValidCouponCode(String id) {
        // A coupon code is valid only if the shopping cart contain at least one product that that coupon belongs to.
        for (String productName: products.keySet()) {
            Product product = Product.getProduct(productName);
            if (product.getCoupon().getCode().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void printReceipt() {
        // You must also support the printing of purchase receipts for the shopping carts.
        // The purchase receipt must include the product name, price, quantity, tax, shipping fee, and date of purchase.
        // The result of printing can be output to the console or stored in a text file. After printing a receipt, no further change can be made to the related shopping cart.
        // When printing a receipt, make sure that you use the latest product information (e.g., a product is updated after you add some of its items to a shopping cart).
        LocalDate purchaseDate = LocalDate.now();
        System.out.printf("Date of purchase: %s\n", purchaseDate.format(DateTimeFormatter.ISO_DATE));
        System.out.printf("Total amount: %.2f\n", cartAmount());
        System.out.printf("Shipping fee: %.2f\n", getShippingFee());
        System.out.printf("Tax: %.2f\n", getCartTax());
        // Print information of products in cart
        displayProducts();
    }

    public double getCartTax() {
        double totalTax = 0;
        for (String productName : products.keySet()) {
            Product product = Product.getProduct(productName);
            int quantity = products.get(productName).getItemQuantity();
            totalTax += product.getTaxAmount() * quantity;
        }
        return totalTax;
    }

    public boolean containsProduct(String productName) {
        return products.get(productName) != null;
    }

    // Getters & Setters

    public static List<ShoppingCart> getCarts() {
        return carts;
    }

    public static void setCarts(List<ShoppingCart> carts) {
        ShoppingCart.carts = carts;
    }

    public Map<String, CartItem> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, CartItem> products) {
        this.products = products;
        for (String productName:products.keySet()) {
            Product product = Product.getProduct(productName);
            int quantity = products.get(productName).getItemQuantity();
            if (product instanceof PhysicalProduct) {
                this.totalWeight += ((PhysicalProduct) product).getWeight() * quantity;
            }
        }
        System.out.println(this.totalWeight);
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Coupon getSelectedCoupon() {
        return this.selectedCoupon;
    }

    public void setSelectedCoupon(Coupon selectedCoupon) {
        this.selectedCoupon = selectedCoupon;
    }
}