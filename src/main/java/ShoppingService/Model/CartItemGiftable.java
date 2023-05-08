package ShoppingService.Model;

public class CartItemGiftable extends CartItem implements CanBeGifted {
    private String msg;

    public CartItemGiftable(Product product, Cart cart, String msg) {
        super(product, cart);
        this.msg = msg;
    }

    public CartItemGiftable(Product product, Cart cart) {
        super(product, cart);
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
