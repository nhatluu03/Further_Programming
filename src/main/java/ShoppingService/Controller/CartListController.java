package ShoppingService.Controller;

import ShoppingService.Model.Cart;
import ShoppingService.Model.CartList;
import ShoppingService.Model.PhysicalProduct;
import ShoppingService.Model.Product;
import ShoppingService.View.CartView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CartListController {
    private final List<Cart> cartList;
    private CartView cartView;
    public CartListController(CartList cartList) {
        this.cartList = cartList.getCartList();
    }

    public void addCart(Cart cart) {
        cartList.add(cart);
        sortCartList();
    }

    public void removeCart(Cart cart) {
        cartList.remove(cart);
    }

    public void sortCartList() {
        cartList.sort(Cart::compareTo);
    }

    public void updateCartList(Cart cart) {
        Iterator<Cart> iterator = cartList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == cart) {
                iterator.remove();
                break;
            }
        }
        cartList.add(cart);
        sortCartList();
    }

    public void updateWeightForCart(Product product) {
        List<Cart> cartsToUpdate = new ArrayList<>();
        for (Cart cart : cartList) {

            if (cart.getProductList().containsKey(product)) {
                double totalWeight = 0;
                for (Map.Entry<Product, Integer> entry : cart.getProductList().entrySet()) {
                    Product productWeight = entry.getKey();
                    int amount = entry.getValue();
                    if (productWeight instanceof PhysicalProduct) {
                        totalWeight += ((PhysicalProduct) productWeight).getWeight() * amount;
                        System.out.println(((PhysicalProduct) productWeight).getWeight());
                        System.out.println(amount);
                        System.out.println(totalWeight);
                    }
                }
                cart.setTotalWeight(totalWeight);
                cartsToUpdate.add(cart);
            }
        }
        for (Cart cart : cartsToUpdate) {
            updateCartList(cart);
        }
    }

}
