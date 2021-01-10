package data;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DigitalSignatureTest {
    @Test
    void testDSignature() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new DigitalSignature( null);
        });
        assertNull(null, exception.getMessage());

    }

    @Test
    void testEquals() {
        DigitalSignature sign1 = new DigitalSignature ("DOCTOR".getBytes());
        DigitalSignature sign2 = new DigitalSignature ("DOCTOR".getBytes());
        DigitalSignature sign3 = new DigitalSignature ("DOCTORA".getBytes());

        assertEquals(sign1, sign1);
        assertEquals(sign1, sign2);
        assertNotEquals(sign1, sign3);
    }

    @Test
    void testToString() {
        DigitalSignature card = new DigitalSignature ("DOCTOR".getBytes());
        String code = "DigitalSignature{" + "Digital Signature='" + Arrays.toString("DOCTOR".getBytes()) + '\'' + '}';

        assertEquals(code,card.toString());
    }

    @Test
    void testHashCode() {
        DigitalSignature sign1 = new DigitalSignature ("DOCTOR".getBytes());
        DigitalSignature sign2 = new DigitalSignature ("DOCTOR".getBytes());

        assertEquals(sign1.hashCode(), sign2.hashCode());
    }

}
