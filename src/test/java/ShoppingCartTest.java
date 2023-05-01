///**
// * @author <Luu Quoc Nhat - S3924942>
// */
//
//import org.example.DigitalProduct;
//import org.example.PhysicalProduct;
//import org.example.Product;
//import org.example.ShoppingCart;
//import org.junit.Before;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class ShoppingCartTest {
//    private ShoppingCart cart;
//    private Product physicalProduct;
//    private Product digitalProduct;
//
//    @Before
//    public void setUp() {
//        cart = new ShoppingCart();
//        physicalProduct = new PhysicalProduct("Book", "A novel by John Doe", 5, 10.0, 0.5);
//        digitalProduct = new DigitalProduct("Music Album", "A collection of songs by Jane Smith", 10, 5.0);
//    }
//
//    @Test
//    public void testAddItemPhysicalProduct() {
//        assertTrue(cart.addItem("Book"));
//        assertEquals(1, cart.getProducts().size());
//        assertEquals(0.5, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testAddItemDigitalProduct() {
//        assertTrue(cart.addItem("Music Album"));
//        assertEquals(1, cart.getProducts().size());
//        assertEquals(0.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testAddItemWithQuantityZero() {
//        physicalProduct.setQuantity(0);
//        assertFalse(cart.addItem("Book"));
//        assertEquals(0, cart.getProducts().size());
//        assertEquals(0.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testAddItemAlreadyInCart() {
//        assertTrue(cart.addItem("Book"));
//        assertFalse(cart.addItem("Book"));
//        assertEquals(1, cart.getProducts().size());
//        assertEquals(0.5, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testRemoveItem() {
//        assertTrue(cart.addItem("Book"));
//        assertTrue(cart.removeItem("Book"));
//        assertEquals(0, cart.getProducts().size());
//        assertEquals(0.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testRemoveItemNotInCart() {
//        assertFalse(cart.removeItem("Book"));
//        assertEquals(0, cart.getProducts().size());
//        assertEquals(0.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testCartAmount() {
//        cart.addItem("Book");
//        cart.addItem("Music Album");
//        assertEquals(15.05, cart.cartAmount(), 0.0);
//    }
//
//    @Test
//    public void testCartAmountWithEmptyCart() {
//        assertEquals(0.0, cart.cartAmount(), 0.0);
//    }
//
//    @Test
//    public void testDisplayItems() {
//        cart.addItem("Book");
//        cart.addItem("Music Album");
//        cart.displayItems(); // manually verify the output
//    }
//
//    @Test
//    public void testCompareTo() {
//        ShoppingCart cart1 = new ShoppingCart();
//        ShoppingCart cart2 = new ShoppingCart();
//        cart1.setTotalWeight(1.0);
//        cart2.setTotalWeight(2.0);
//        assertTrue(cart1.compareTo(cart2) < 0);
//        assertTrue(cart2.compareTo(cart1) > 0);
//        assertTrue(cart1.compareTo(cart1) == 0);
//    }
//}
