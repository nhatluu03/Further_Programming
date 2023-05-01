/**
 * @author <Luu Quoc Nhat - S3924942>
 */

import org.example.PhysicalProduct;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhysicalProductTest {

    @Test
    public void testPhysicalProduct() {
        String name = "Product01";
        String description = "Product 01 is a physical item";
        int quantity = 10;
        double price = 20.0;
        double weight = 1.5;

        PhysicalProduct product = new PhysicalProduct(name, description, quantity, price, weight);

        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(quantity, product.getQuantity());
        assertEquals(price, product.getPrice(), 0.001);
        assertEquals(weight, product.getWeight(), 0.001);

        product.setName("New name of product01");
        assertEquals("New name of product01", product.getName());

        product.setDescription("New description of product01");
        assertEquals("New description of product01", product.getDescription());

        product.setQuantity(5);
        assertEquals(5, product.getQuantity());

        product.setPrice(30.0);
        assertEquals(30.0, product.getPrice(), 0.001);

        product.setWeight(2.0);
        assertEquals(2.0, product.getWeight(), 0.001);

        product.increaseQuantity();
        assertEquals(6, product.getQuantity());

        product.decreaseQuantity();
        assertEquals(5, product.getQuantity());

        assertEquals("PHYSICAL - New name of product01", product.toString());
    }
}
