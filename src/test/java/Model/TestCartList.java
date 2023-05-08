//package ShoppingService;
//
///**
// * @author Nguyen Nhat Minh - s3932112
// */
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//
//public class TestCartList {
//
//    private CartList cartList;
//    private Cart cart1;
//    private Cart cart2;
//
//    @Before
//    public void setUp() {
//        cartList = new CartList();
//        cart1 = new Cart();
//        cart2 = new Cart();
//    }
//
//    @Test
//    public void testAddCart() {
//        cartList.addCart(cart1);
//        cartList.addCart(cart2);
//
//        List<Cart> expected = new ArrayList<>();
//        expected.add(cart1);
//        expected.add(cart2);
//
//        assertEquals(expected, cartList.getCartList());
//    }
//
//    @Test
//    public void testRemoveCart() {
//        cartList.addCart(cart1);
//        cartList.addCart(cart2);
//
//        cartList.removeCart(cart1);
//
//        List<Cart> expected = new ArrayList<>();
//        expected.add(cart2);
//
//        assertEquals(expected, cartList.getCartList());
//    }
//
//    @Test
//    public void testSortCartList() {
//        CartList cartList = new CartList();
//        Cart cart1 = new Cart();
//        cart1.setTotalWeight(10);
//        Cart cart2 = new Cart();
//        cart2.setTotalWeight(5);
//        Cart cart3 = new Cart();
//        cart3.setTotalWeight(15);
//        cartList.addCart(cart1);
//        cartList.addCart(cart2);
//        cartList.addCart(cart3);
//        cartList.sortCartList();
//        assertEquals(cart3, cartList.getCartList().get(2));
//        assertEquals(cart1, cartList.getCartList().get(1));
//        assertEquals(cart2, cartList.getCartList().get(0));
//    }
//    @Test
//    public void testUpdateCartList() {
//        cartList.addCart(cart1);
//        cartList.addCart(cart2);
//
//        cart1.addItem(new PhysicalProduct("testUpdateCartList", "Description 1", 10, 10.0, 1.0));
//
//        cartList.updateCartList(cart1);
//
//        List<Cart> expected = new ArrayList<>();
//        expected.add(cart2);
//        expected.add(cart1);
//
//        assertEquals(expected, cartList.getCartList());
//    }
//
//    @Test
//    public void testUpdateWeightForCart() {
//        cartList.addCart(cart1);
//        cartList.addCart(cart2);
//
//        PhysicalProduct product1 = new PhysicalProduct("testUpdateWeightForCart", "Description 1", 5, 10.0, 1.0);
//        PhysicalProduct product2 = new PhysicalProduct("testUpdateWeightForCart2", "Description 2", 5, 10.0, 2.5);
//
//        cart1.addItem(product1);
//        cart1.addItem(product2);
//        cartList.updateCartList(cart1);
//
//        cart2.addItem(product1);
//        cartList.updateCartList(cart2);
//
//        product1.setWeight(1.5);
//
//        cartList.updateWeightForCart(product1);
//
//
//        double expectedWeight1 = product1.getWeight() + product2.getWeight();
//        double expectedWeight2 = product1.getWeight();
//
//        assertEquals(expectedWeight1, cart1.getTotalWeight(), 0.0);
//        assertEquals(expectedWeight2, cart2.getTotalWeight(), 0.0);
//    }
//
//
//
//}