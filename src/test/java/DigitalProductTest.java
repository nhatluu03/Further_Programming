/**
 * @author <Luu Quoc Nhat - S3924942>
 */

import org.example.DigitalProduct;
import org.junit.Test;

import static org.junit.Assert.*;

public class DigitalProductTest {

    @Test
    public void testDigitalProduct() {
        String name = "Product02";
        String description = "Product02 is a digital item";
        int quantity = 5;
        double price = 15.0;

        DigitalProduct product = new DigitalProduct(name, description, quantity, price);

        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(quantity, product.getQuantity());
        assertEquals(price, product.getPrice(), 0.001);

        product.setName("New name of product02");
        assertEquals("New name of product02", product.getName());

        product.setDescription("New description of product02");
        assertEquals("New description of product02", product.getDescription());

        product.setQuantity(10);
        assertEquals(10, product.getQuantity());

        product.setPrice(20.0);
        assertEquals(20.0, product.getPrice(), 0.001);

        product.increaseQuantity();
        assertEquals(11, product.getQuantity());

        product.decreaseQuantity();
        assertEquals(10, product.getQuantity());

        assertEquals("DIGITAL - New name of product02", product.toString());
    }
}