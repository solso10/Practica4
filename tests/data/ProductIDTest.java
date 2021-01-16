package data;

import exceptions.ProductIDException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIDTest {
    @Test
    public void nullCode() {
        Throwable exception = assertThrows(NullPointerException.class, () -> {
            new ProductID(null);
        });
        assertEquals("El código no puede ser null", exception.getMessage());
    }

    @Test
    public void emptyCode() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new ProductID("");
        });
        assertEquals("El código no puede ser vacío", exception.getMessage());
    }

    @Test
    public void noNumericCode() {
        Throwable exception = assertThrows(ProductIDException.class, () -> {
            new ProductID("09838aft78");
        });
        assertEquals("El código debe estar formado por numeros", exception.getMessage());
    }

    @Test
    public void validCode() throws ProductIDException {
        ProductID prodId = new ProductID("0983878");
        assertEquals("0983878", prodId.getProductID());
    }
}
