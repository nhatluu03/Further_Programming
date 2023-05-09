package Model;

import ShoppingService.Model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestCartItem {
    @Test
    public void testCreateCartItem() {
        PhysicalProduct physicalProduct = new PhysicalProduct("Book", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");
        PhysicalProduct physicalProduct2 = new PhysicalProduct("Book2", "A novel by John Doe", 5, 10.0, 0.5, false, "LuxuryTaxRate");
        Cart cart = new Cart();
        CartItem item = new CartItemGiftable(physicalProduct, cart, "msg");
        CartItem item2 = new CartItemNotGiftable(physicalProduct2, cart);
        assertEquals(item2.getMsg(), "The product item is not giftable");
        assertEquals(item.getMsg(), "msg");

    }


}
