/**
 * @author <Luu Quoc Nhat - S3924942>
 */

import org.example.DigitalGift;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DigitalGiftTest {

    @Test
    public void testGetMessage() {
        DigitalGift gift = new DigitalGift("Gift Card", "A digital gift card", 1, 50.0);
        String message = "Happy Birthday!";
        gift.setMessage(message);
        assertEquals(message, gift.getMessage());
    }

    @Test
    public void testToString() {
        DigitalGift gift = new DigitalGift("Gift Card", "A digital gift card", 1, 50.0);
        assertEquals("DIGITAL - Gift Card", gift.toString());
    }
}
