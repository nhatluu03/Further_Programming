package Model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ShoppingService.Model.*;

public class CartItemListTest {
    private CartItemList cartItemList;
    private Product product1;
    private Product product2;
    private CartItemNotGiftable item1;
    private CartItemGiftable item2;

    @Test
    public void testAddCartItem() {
        Cart cart = new Cart();
        cartItemList = new CartItemList();
        product1 = new PhysicalProduct("Book3", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");
        product2 = new PhysicalProduct("Book4", "A novel by John Doe", 5, 10.0, 0.5, false, "LuxuryTaxRate");
        item1 = new CartItemNotGiftable(product1, cart);
        item2 = new CartItemGiftable(product2, cart);
        cartItemList.addCartItem(item1);
        assertEquals(1, cartItemList.getCartItemsList().size());
        assertEquals(item1, cartItemList.getCartItemsList().get(0));
        cartItemList.addCartItem(item2);
        assertEquals(2, cartItemList.getCartItemsList().size());
        assertEquals(item2, cartItemList.getCartItemsList().get(1));
    }

    @Test
    public void testRemoveCartItem() {
        Cart cart = new Cart();
        cartItemList = new CartItemList();
        product1 = new PhysicalProduct("Book5", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");
        product2 = new PhysicalProduct("Book6", "A novel by John Doe", 5, 10.0, 0.5, false, "LuxuryTaxRate");
        item1 = new CartItemNotGiftable(product1, cart);
        item2 = new CartItemGiftable(product2, cart);
        cartItemList.addCartItem(item1);
        cartItemList.addCartItem(item2);
        cartItemList.removeCartItem(item1);
        assertEquals(1, cartItemList.getCartItemsList().size());
        assertFalse(cartItemList.getCartItemsList().contains(item1));
        assertTrue(cartItemList.getCartItemsList().contains(item2));
    }

    @Test
    public void testRemoveProduct() {
        Cart cart = new Cart();
        cartItemList = new CartItemList();
        product1 = new PhysicalProduct("Book7", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");
        product2 = new PhysicalProduct("Book8", "A novel by John Doe", 5, 10.0, 0.5, false, "LuxuryTaxRate");
        item1 = new CartItemNotGiftable(product1, cart);
        item2 = new CartItemGiftable(product2, cart);
        cartItemList.addCartItem(item1);
        cartItemList.addCartItem(item2);
        cartItemList.removeProduct(product1);
        assertEquals(1, cartItemList.getCartItemsList().size());
        assertFalse(cartItemList.getCartItemsList().contains(item1));
        assertTrue(cartItemList.getCartItemsList().contains(item2));
    }

    @Test
    public void testRemoveMessage() {
        Cart cart = new Cart();
        cartItemList = new CartItemList();
        product1 = new PhysicalProduct("Book9", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");
        product2 = new PhysicalProduct("Book0", "A novel by John Doe", 5, 10.0, 0.5, false, "LuxuryTaxRate");
        item1 = new CartItemNotGiftable(product2, cart);
        item2 = new CartItemGiftable(product1, cart);
        cartItemList.addCartItem(item1);
        cartItemList.addCartItem(item2);
        item1.setMsg("msg1");
        item2.setMsg("msg2");
        cartItemList.removeMessage(product2);
        assertEquals("The product item is not giftable", item1.getMsg());
        assertEquals("msg2", item2.getMsg());
    }

    @Test
    public void testEditMessage() {
        Cart cart = new Cart();
        cartItemList = new CartItemList();
        product1 = new PhysicalProduct("Boo9", "A novel by John Doe", 5, 10.0, 0.5, true, "LuxuryTaxRate");
        product2 = new PhysicalProduct("Boo0", "A novel by John Doe", 5, 10.0, 0.5, false, "LuxuryTaxRate");
        item1 = new CartItemNotGiftable(product2, cart);
        item2 = new CartItemGiftable(product1, cart);
        cartItemList.addCartItem(item1);
        cartItemList.addCartItem(item2);
        cartItemList.editMessage(item2, "msgg");
        assertEquals(2, cartItemList.getCartItemsList().size());
        assertEquals("The product item is not giftable", item1.getMsg());
        assertEquals("msgg", item2.getMsg());
    }

}