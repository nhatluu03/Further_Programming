package ShoppingService.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartItemList implements Iterable<CartItem> {
    private final List<CartItem> cartItemsList;

    public CartItemList() {
        cartItemsList = new ArrayList<>();
    }

    public void addCartItem(CartItem cartItem) {
        cartItemsList.add(cartItem);
        sortCartList();
    }

    public void removeCartItem(CartItem cartItem) {
        cartItemsList.remove(cartItem);
    }

    public void removeProduct(Product product) {
        cartItemsList.removeIf(cartItem -> cartItem.getProduct().equals(product));
    }

    public void removeMessage(Product product) {
        for (int i = 0; i < cartItemsList.size(); i ++) {
            if (cartItemsList.get(i).getProduct().equals(product)) {
                cartItemsList.get(i).setMsg(null);
            }
        }
        System.out.println("The message of the product items are removed!");
    }

    public void editMessage(CartItem item, String msg) {
        cartItemsList.remove(item);
        item.setMsg(msg);
        cartItemsList.add(item);
    }


    public List<CartItem> getCartItemsList() {
        return cartItemsList;
    }


    public void sortCartList() {
        cartItemsList.sort(CartItem::compareTo);
    }

    public void updateCartList(CartItem cartItem) {
        Iterator<CartItem> iterator = cartItemsList.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == cartItem) {
                iterator.remove();
                break;
            }
        }
        cartItemsList.add(cartItem);
        sortCartList();
    }

    public CartItem getItem (int index) {
        return cartItemsList.get(index);
    }

    public void clearCartList() {
        cartItemsList.clear();
    }

    @Override
    public Iterator<CartItem> iterator() {
        return cartItemsList.iterator();
    }

    public void displayCartItems() {
        for (int i = 0; i < cartItemsList.size(); i++) {
            System.out.printf("\t - (%s) %s : %s\n", i + 1, getItem(i).getProduct(), getItem(i).getMsg());
        }
    }

}