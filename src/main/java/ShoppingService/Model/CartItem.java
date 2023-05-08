package ShoppingService.Model;

public abstract class CartItem {
    private Product product;
    private Cart currentCart;

    public CartItem (Product product, Cart cart) {
        this.product = product;
        this.currentCart = cart;
    }

    public abstract String getMsg();

    public abstract void setMsg(String msg);

    public Product getProduct() {
        return product;
    }

    public Cart getCurrentCart() {
        return currentCart;
    }

    public int compareTo(CartItem other) {
        return this.getProduct().getName().compareTo(other.getProduct().getName());
    }
}
