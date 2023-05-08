///**
// * @author <Luu Quoc Nhat - S3924942>
// */
//
//import org.example.PhysicalGift;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class PhysicalGiftTest {
//
//    @Test
//    public void testSetAndGetMessage() {
//        PhysicalGift gift = new PhysicalGift("Test Gift", "A test physical gift", 1, 10.0, 0.5);
//        assertNull(gift.getMessage());
//        gift.setMessage("Test message");
//        assertEquals("Test message", gift.getMessage());
//    }
//
//    @Test
//    public void testToString() {
//        PhysicalGift gift = new PhysicalGift("Test Gift", "A test physical gift", 1, 10.0, 0.5);
//        assertEquals("PHYSICAL - Test Gift", gift.toString());
//    }
//
//    @Test
//    public void testGetWeight() {
//        PhysicalGift gift = new PhysicalGift("Test Gift", "A test physical gift", 1, 10.0, 0.5);
//        assertEquals(0.5, gift.getWeight(), 0.0001);
//    }
//}