package View;

import ShoppingService.Model.PhysicalProduct;
import ShoppingService.Model.Product;
import ShoppingService.View.ProductView;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProductView {

    @Test
    void testDisplayProduct() {
        // Create a sample product
        Product product = new PhysicalProduct("Sony PlayStation 06", "This is a Disc Edition", 10, 19.99, 1.0, true, "NormalTaxRate");

        // Redirect system output to capture the printed content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Call the displayProduct method
        ProductView.displayProduct(product);

        // Restore the original system output
        System.setOut(originalOut);

        String expectedOutput = "Physical  Sony PlayStation 06                19.99     NormalTaxRate       10        1.0       Yes       This is a Disc Edition";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }
}
