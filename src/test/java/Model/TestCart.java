//package ShoppingService;
//
///**
// * @author Nguyen Nhat Minh - s3932112
// */
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class TestCart {
//
//    @Test
//    public void testAddItem() {
//        // Create a new cart and product
//        Cart cart = new Cart();
//        Product product = new PhysicalProduct("testAddItem", "Description 1", 10, 10.0, 1.0);
//
//        // Add the product to the cart
//        boolean result = cart.addItem(product);
//
//        // Check that the product was added successfully
//        assertTrue(result);
//        assertTrue(cart.getItems().contains("testAddItem"));
//        assertEquals(1.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testAddItemOutOfStock() {
//        // Create a new cart and product with no quantity available
//        Cart cart = new Cart();
//        Product product = new PhysicalProduct("testAddItemOutOfStock", "Description", 0, 10.0, 1.0);
//
//        // Add the product to the cart
//        boolean result = cart.addItem(product);
//
//        // Check that the product was not added
//        assertFalse(result);
//        assertFalse(cart.getItems().contains("testAddItemOutOfStock"));
//        assertEquals(0.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testAddItemAlreadyInCart() {
//        // Create a new cart and product
//        Cart cart = new Cart();
//        Product product = new PhysicalProduct("testAddItemAlreadyInCart", "Description", 10, 10.0, 1.0);
//
//        // Add the product to the cart twice
//        boolean result1 = cart.addItem(product);
//        boolean result2 = cart.addItem(product);
//
//        // Check that the product was not added the second time
//        assertTrue(result1);
//        assertFalse(result2);
//        assertTrue(cart.getItems().contains("testAddItemAlreadyInCart"));
//        assertEquals(1.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testRemoveItem() {
//        // Create a new cart and product
//        Cart cart = new Cart();
//        Product product = new PhysicalProduct("testRemoveItem", "Description", 10, 10.0, 1.0);
//
//        // Add the product to the cart and then remove it
//        cart.addItem(product);
//        boolean result = cart.removeItem(product);
//
//        // Check that the product was removed successfully
//        assertTrue(result);
//        assertFalse(cart.getItems().contains("testRemoveItem"));
//        assertEquals(0.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testRemoveItemNotInCart() {
//        // Create a new cart and product
//        Cart cart = new Cart();
//        Product product = new PhysicalProduct("testRemoveItemNotInCart", "Description", 10, 10.0, 1.0);
//
//        // Remove the product from an empty cart
//        boolean result = cart.removeItem(product);
//
//        // Check that the product was not removed
//        assertFalse(result);
//        assertFalse(cart.getItems().contains("testRemoveItemNotInCart"));
//        assertEquals(0.0, cart.getTotalWeight(), 0.0);
//    }
//
//    @Test
//    public void testCartAmount() {
//        // Create a new cart and add some products
//        Cart cart = new Cart();
//        Product product1 = new PhysicalProduct("testCartAmountP", "Description", 10, 20.0, 1.0);
//        Product product2 = new DigitalProduct("testCartAmountD", "Description", 10, 10.0);
//
//        cart.addItem(product1);
//        cart.addItem(product2);
//
//        // Check the cart amount
//        assertEquals(30.1, cart.cartAmount(), 0.0);
//
//        // Add a physical product to test shipping fee
//        Product product3 = new PhysicalProduct("testCartAmountP2", "Description", 10, 20.0, 2.0);
//        cart.addItem(product3);
//
//        // Check the cart amount with shipping fee
////        assertEquals(3.0, cart.getTotalWeight(),0.0);
//        assertEquals(50.3, cart.cartAmount(), 0.0);
//    }
//
//    @Test
//    public void testGetTotalWeight() {
//        PhysicalProduct physicalProduct1 = new PhysicalProduct("testGetTotalWeight", "Description", 10, 20.0, 1.0);
//        PhysicalProduct physicalProduct2 = new PhysicalProduct("testGetTotalWeight2", "Description", 10, 30.0, 2.0);
//        Cart cart = new Cart();
//        assertEquals(0, Double.compare(0.0, cart.getTotalWeight()));
//        assertTrue(cart.addItem(physicalProduct1));
//        assertEquals(0, Double.compare(1.0, cart.getTotalWeight()));
//        assertTrue(cart.addItem(physicalProduct2));
//        assertEquals(0, Double.compare(3.0, cart.getTotalWeight()));
//    }
//
//    @Test
//    public void testSetTotalWeight() {
//        Cart cart = new Cart();
//        assertEquals(0, Double.compare(0.0, cart.getTotalWeight()));
//        cart.setTotalWeight(5.0);
//        assertEquals(0, Double.compare(5.0, cart.getTotalWeight()));
//    }
//
//    @Test
//    public void testCompareTo() {
//
//        PhysicalProduct physicalProduct1 = new PhysicalProduct("testCompareTo", "Description", 10, 20.0, 1.0);
//        PhysicalProduct physicalProduct2 = new PhysicalProduct("testCompareTo2", "Description", 10, 30.0, 2.0);
//
//        Cart cart = new Cart();
//        Cart cart1 = new Cart();
//        Cart cart2 = new Cart();
//
//        assertEquals(0, cart.compareTo(cart1));
//        assertEquals(0, cart1.compareTo(cart2));
//        assertEquals(0, cart.compareTo(cart2));
//
//        cart.addItem(physicalProduct1);
//        cart1.addItem(physicalProduct1);
//        cart1.addItem(physicalProduct2);
//        assertTrue(cart.compareTo(cart1) < 0);
//        assertTrue(cart.compareTo(cart2) > 0);
//        assertTrue(cart1.compareTo(cart2) > 0);
//
////
//    }
//
//
//
//}