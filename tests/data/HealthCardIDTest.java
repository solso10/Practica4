package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HealthCardIDTest {
    @Test
    public void nullCode(){
        Throwable exception = assertThrows(NullPointerException.class,()->{
            new HealthCardID(null);
        });
        assertEquals("El código no puede ser null", exception.getMessage());
    }

    @Test
    public void emptyCode(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->{
            new HealthCardID("");
        });
        assertEquals("El código no puede ser vacío", exception.getMessage());
    }

    @Test
    public void notValidCode(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->{
            new HealthCardID("ABC123456789");
        });
        assertEquals("El código debe estar formado por 14 dígitos: 4 letras y 10 numeros",
                exception.getMessage());
    }

    @Test
    public void validCode(){
        HealthCardID id = new HealthCardID("ABCD1234567890");
        assertEquals("ABCD1234567890", id.getPersonalID());
    }

}
