/**
 * @author <Luu Quoc Nhat - S3924942>
 */

import org.example.PhysicalProduct;
import org.example.Product;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    // Create a Physical Product instance to test methods of class Product
    private Product product;

    private void setUp() {
        product = new PhysicalProduct("Pen", "A ballpoint pen", 10, 1.99, 0.02);
    }

    @Test
    public void testGetProduct() {
        setUp();
        Product p = Product.getProduct("Pen");
        assertNotNull(p);
        assertEquals("Pen", p.getName());
        assertEquals("A ballpoint pen", p.getDescription());
        assertEquals(10, p.getQuantity());
        assertEquals(1.99, p.getPrice(), 0.001);
        assertTrue(p instanceof PhysicalProduct);
        assertEquals(0.02, ((PhysicalProduct) p).getWeight(), 0.001);
    }

    @Test
    public void testSettersAndGetters() {
        setUp();
        product.setName("Red Pen");
        assertEquals("Red Pen", product.getName());
        product.setDescription("A red ballpoint pen");
        assertEquals("A red ballpoint pen", product.getDescription());
        product.setQuantity(20);
        assertEquals(20, product.getQuantity());
        product.setPrice(2.49);
        assertEquals(2.49, product.getPrice(), 0.001);
        ((PhysicalProduct) product).setWeight(0.03);
        assertEquals(0.03, ((PhysicalProduct) product).getWeight(), 0.001);
    }

    @Test
    public void testIncreaseAndDecreaseQuantity() {
        setUp();
        product.increaseQuantity();
        assertEquals(11, product.getQuantity());
        product.decreaseQuantity();
        assertEquals(10, product.getQuantity());
    }

    @Test
    public void testToString() {
        setUp();
        assertEquals("PHYSICAL - Pen", product.toString());
    }
}
