package ShoppingService.View;

/**
 * @author Nguyen Nhat Minh - s3932112
 */

import ShoppingService.Controller.*;
import ShoppingService.Model.Cart;
import ShoppingService.Controller.CartController;
import ShoppingService.Model.CartList;
import ShoppingService.Model.Product;
import ShoppingService.Utils.Validations;

import java.io.IOException;

import static ShoppingService.Controller.ShopManager.*;
import static ShoppingService.Controller.ShopManager.displayCartDetails;
import static ShoppingService.View.CartView.printReceipt;

public class Main {
    public static void main(String[] args) throws IOException {
        // Initialize a default empty cart for users
        currentCart = new Cart();

        // Initialize cartList 's controller, and add current cart to
        CartList cartList = new CartList();
        CartListController cartListController = new CartListController(cartList);
        cartListController.addCart(currentCart);
        cartListController.sortCartList();

        // Load products from text file
        String productFile = "Data/products.txt";
        loadProductsFromFile(productFile);
        boolean quit = false;

        // Load carts from text file
        String cartFile = "Data/carts.txt";
        loadCartsFromFile(cartFile);

        // Display menu with available options
        while (!quit) {
            displayMenu();
            int option = Validations.validateNumber(sc.nextLine(), sc);

            switch (option) {
                case 1 -> createNewProduct();
                case 2 -> editProduct();
                case 3 -> createCoupon();
                case 4 -> displayProducts();
                case 5 -> {
                    Product.iterateUsingForEach();
                    pauseToRead();
                }
                case 6 -> displayProductDetails();

                case 7 -> createNewCart();
                case 8 -> chooseCart();
                case 9 -> addProductToCart();
                case 10 -> removeProductFromCart();
                case 11 -> updateProductMessageInCart();
                case 12 -> applyCouponToCart();
                case 13 -> cartList.displayCarts();
                case 14 -> displayCartDetails();
                case 15 -> printReceipt(currentCart);
                case 16 -> quit = true;
                default -> System.out.println("Invalid entry! Please try again.");
            }
        }
    }
}
