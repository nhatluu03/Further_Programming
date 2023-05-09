package ShoppingService.Controller;

import ShoppingService.Model.*;
import ShoppingService.Utils.Validations;
import ShoppingService.View.CartView;

import java.util.*;

public class CartController {
    private Cart cart;
    private CartView cartView;
    public CartController(Cart cart) {
        this.cart = cart;
    }
    public boolean addItem(Product product) {
        cart.getItems().addCartItem(new CartItemNotGiftable(product, cart));
        Map<Product, Integer> productList = cart.getProductList();
        if (cart.getProductList().containsKey(product)) {
            productList.put(product, productList.get(product) + 1);
        } else {
            productList.put(product, 1);
        }
        if (product instanceof PhysicalProduct) {
            double newTotalWeight = cart.getTotalWeight() + ((PhysicalProduct) product).getWeight();
            cart.setTotalWeight(newTotalWeight);
        }
        return true;
    }

    public boolean addItem(Product product, String msg) {
        cart.getItems().addCartItem(new CartItemGiftable(product, cart, msg));
        Map<Product, Integer> productList = cart.getProductList();
        if (productList.containsKey(product)) {
            productList.put(product, productList.get(product) + 1);
        } else {
            productList.put(product, 1);

        }
        if (product instanceof PhysicalProduct) {
            double newTotalWeight = cart.getTotalWeight() + ((PhysicalProduct) product).getWeight();
            cart.setTotalWeight(newTotalWeight);
        }
        return true;
    }

    public boolean addItem(Product product, int amount, Scanner sc) {
        String option;
        Map<Product, Integer> productList = cart.getProductList();
        if (product.getQuantityAvailable() < amount || productList.containsKey(product)) {
            System.out.println("Either the product is out of stock or the cart has this item already!");
            System.out.println("Do you want to adjust the amount of the item? y/n");
            option = sc.nextLine();
            if (option.equals("y")) {
                changeAmount(product, sc);
                return true;
            }
            return false;
        }

        for (int i = 0; i < amount; i++) {
            CartItem newItem = (product.isGiftable()) ? new CartItemGiftable(product, this.cart) : new CartItemNotGiftable(product, cart);

            if (newItem instanceof  CartItemGiftable) {
                System.out.println("The item is giftable, do you want to write a message? y/n");
                option = sc.nextLine();
                boolean value = Validations.validateBoolean(option, sc);
                if (value) {
                    String msg = sc.nextLine();
                    newItem.setMsg(msg);
                    System.out.println("Message added!");
                }
            }
            cart.getItems().addCartItem(newItem);
            int quantityAvailable = product.getQuantityAvailable();
            product.setQuantityAvailable(quantityAvailable-1);
        }

        System.out.println("Product added to cart!");
        applyCoupon(sc);

        if (product instanceof PhysicalProduct) {
            double newTotalWeight = cart.getTotalWeight() + ((PhysicalProduct) product).getWeight() * amount;
            cart.setTotalWeight(newTotalWeight);
        }

        productList.put(product, amount);


        return true;
    }

    public boolean removeItem(CartItem item) {
        cart.getItems().removeCartItem(item);
        Map<Product, Integer> productList = cart.getProductList();
        productList.put(item.getProduct(), productList.get(item.getProduct()) - 1);
        if (productList.get(item.getProduct()) == 0) {
            productList.remove(item.getProduct());
            System.out.println("Product removed from list!");
            if (cart.getCoupon().getProduct().equals(item.getProduct())) {
                cart.setCoupon(null);
                System.out.println("Coupon removed since no product with the corresponding coupon existed!");
            }
        }
        if (item.getProduct() instanceof  PhysicalProduct) {
            double newTotalWeight = cart.getTotalWeight() - ((PhysicalProduct) item.getProduct()).getWeight();
            cart.setTotalWeight(newTotalWeight);
        }
        System.out.println("Item removed from cart!");
        return true;
    }


    public boolean changeAmount(Product product, Scanner sc) {
        int newAmount;
        Map<Product, Integer> productList = cart.getProductList();
        System.out.printf("The old amount is %s, please input the new one: \n", productList.get(product));
        String input = sc.nextLine();
        newAmount = Validations.validateNumber(input, sc);
        productList.put(product, newAmount);
        System.out.println("Changed successfully!");
        return true;
    }

    public boolean applyCoupon(Scanner sc) {
        if (cart.printCoupons()) {
            System.out.println("Enter the name of product to be applied coupon:");
            String productName = sc.nextLine();
            Product selectedProduct;
            if (Product.checkIfNameExisted(productName)) {
                selectedProduct = Product.getProduct(productName);
            } else {
                System.out.println("Product not found");
                return false;
            }
            if (!cart.getProductList().containsKey(selectedProduct)) {
                System.out.println("This cart does not have this product!");
                return false;
            }


            System.out.println("List of the corresponding coupons are: ");
            System.out.println(selectedProduct.getCoupons());

            System.out.println("Please enter the coupon id:");
            String couponCode = sc.nextLine();
            Coupon coupon = Coupon.getCoupon(couponCode);
            if (coupon == null) {
                System.out.println("Invalid coupon code!");
                return false;
            }

            CouponProduct appliedCoupon = new CouponProduct(coupon, selectedProduct);
            cart.setCoupon(appliedCoupon);
            System.out.println("Coupon applied successfully!");
            return true;
        } else {
            return false;
        }
    }
}