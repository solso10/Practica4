package data;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

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
        String signature1 = "Sign1";
        String signature2 = "Sign2";
        String signature3 = "Sign3";
        String signatureR = "Sign1";

        DigitalSignature digSign1 = new DigitalSignature (signature1.getBytes());
        DigitalSignature digSign2 = new DigitalSignature (signature2.getBytes());
        DigitalSignature digSign3 = new DigitalSignature (signature3.getBytes());
        DigitalSignature digSignR = new DigitalSignature (signatureR.getBytes());

        assertEquals(digSign1, digSignR);
        assertEquals(digSign1, digSign1);
        assertNotEquals(digSign2, digSign3);
    }

    @Test
    void testToString() {
        String signature = "Sign1";
        String digSign1 = "DigitalSignature{" + "Digital Signature='" + new String(signature.getBytes(),StandardCharsets.UTF_8) + '\'' + '}';

        DigitalSignature digSign2 = new DigitalSignature (signature.getBytes());

        assertEquals(digSign1,digSign2.toString());
    }

    @Test
    void testHashCode() {
        String signature1 = "Sign1";
        String signatureR = "Sign1";

        DigitalSignature digSign1 = new DigitalSignature (signature1.getBytes());
        DigitalSignature digSign2 = new DigitalSignature (signatureR.getBytes());

        assertEquals(digSign1.hashCode(), digSign2.hashCode());
    }

}
