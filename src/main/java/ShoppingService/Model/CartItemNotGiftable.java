package ShoppingService.Model;

public class CartItemNotGiftable extends CartItem {
    public CartItemNotGiftable (Product product, Cart cart) {
        super(product, cart);
    }

    @Override
    public String getMsg() {
        return "The product item is not giftable";
    }

    @Override
    public void setMsg(String msg) {

    }
}
