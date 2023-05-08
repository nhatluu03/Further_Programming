//package ShoppingService;
///**
// * @author Nguyen Nhat Minh - s3932112
// */
//
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class TestEditProduct {
//    @Test
//    public void testUpdateProductWeight() {
//        CartList cartList = new CartList();
//        PhysicalProduct product = new PhysicalProduct("testUpdateProductWeight", "Description", 10, 20.0, 0.5);
//
//        Cart cart = new Cart();
//
//        boolean result = cart.addItem(product);
//        cartList.addCart(cart);
//        assertTrue(result);
//        assertTrue(cart.cartAmount() > 0);
//        assertEquals(0.5, cart.getTotalWeight());
//
//        product.setWeight(0.6);
//        assertEquals(0.6, product.getWeight());
//        cartList.updateWeightForCart(product);
//
//        assertEquals(0.6, cart.getTotalWeight());
//
//    }
//
//
//
//}
