package ShoppingService;

/**
 * @author Nguyen Nhat Minh - s3932112
 */

import ShoppingService.Model.DigitalProduct;
import ShoppingService.Model.PhysicalProduct;
import ShoppingService.Model.Product;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestProduct {

    @Test
    void testProductCreation() {
        Product product = new PhysicalProduct("TestProduct", "Test description", 10, 19.99,1.0,  true, "FreeTaxRate");
//        new DigitalProduct(name, description, quantityAvailableInt, price, isGiftable, taxType);
        assertEquals("TestProduct", product.getName());
        assertEquals("Test description", product.getDescription());
        assertEquals(10, product.getQuantityAvailable());
        assertEquals(19.99, product.getPrice(), 0.01);
        assertTrue(product.isGiftable());
        assertEquals(0.0, product.getTax());
    }

    @Test
    void testSetQuantityAvailableWithString() {
        Product product = new PhysicalProduct("testSetQuantityAvailableWithString", "Test description", 5, 19.99,1.0,  true, "FreeTaxRate");
        product.setQuantityAvailable("5");
        assertEquals(5, product.getQuantityAvailable());
    }

    @Test
    void testSetQuantityAvailableWithInvalidString() {
        Product product = new PhysicalProduct("testSetQuantityAvailableWithInvalidString", "Test description", 10, 19.99,1.0,  true, "FreeTaxRate");
        product.setQuantityAvailable(5);
        assertEquals(5, product.getQuantityAvailable());
    }

    @Test
    void testCheckIfNameExisted() {
        Product product = new PhysicalProduct("testCheckIfNameExisted", "Test description", 10, 19.99,1.0,  true, "FreeTaxRate");
        assertTrue(Product.checkIfNameExisted("testCheckIfNameExisted"));
        assertFalse(Product.checkIfNameExisted("NonExistingProduct"));
    }

    @Test
    void testGetProduct() {
        Product product = new PhysicalProduct("testGetProduct", "Test description", 10, 19.99,1.0,  true, "FreeTaxRate");
        assertEquals(product, Product.getProduct("testGetProduct"));
        assertNull(Product.getProduct("NonExistingProduct"));
    }

    @Test
    public void testUpdateDigitalProductInformation() {
        DigitalProduct product = new DigitalProduct("testUpdateDigitalProductInformation", "Description", 10, 20.0, true, "LuxuryTaxRate");
        product.setDescription("NewDescription");
        product.setPrice(30.0);
        assertEquals("NewDescription", product.getDescription());
        assertEquals(30.0, product.getPrice(), 0.0);
    }

    @Test
    public void testUpdatePhysicalProductInformation() {
        PhysicalProduct product = new PhysicalProduct("testUpdatePhysicalProductInformation", "Description", 10, 20.0, 5.0, true, "NormalTaxRate");
        product.setDescription("NewDescription");
        product.setPrice(30.0);
        product.setWeight(10.0);
        assertEquals("NewDescription", product.getDescription());
        assertEquals(30.0, product.getPrice(), 0.0);
        assertEquals(10.0, product.getWeight(),0.0);
    }

    @Test
    public void testIsGiftableProduct() {
        PhysicalProduct product = new PhysicalProduct("testIsGiftableProduct", "Description", 10, 20.0, 5.0, false, "NormalTaxRate");

    }


}

