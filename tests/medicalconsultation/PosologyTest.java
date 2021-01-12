package medicalconsultation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PosologyTest {

    private Posology posology;

    @BeforeEach
    void setUp() {

        posology = new Posology(7f,2f,FqUnit.DAY);
    }

    @Test
    public void TestgetDose() {
        assertEquals(posology.getDose(), 7f);
        assertNotEquals(posology.getDose(), 6f);

    }

    @Test
    public void TestgetFreq(){

        assertEquals(posology.getFreq(), 2f);
        assertNotEquals(posology.getFreq(), 3f);

    }

    @Test
    public void TestgetFqUnit() {
        assertEquals(posology.getFreqUnit(), FqUnit.DAY);
        assertNotEquals(posology.getFreqUnit(), FqUnit.MONTH);

    }

    @Test
    public void TestsetDose(){
        float dose = 3f;
        posology.setDose(dose);
        assertEquals(posology.getDose(),dose);
    }

    @Test
    public void TestsetFreg(){
        float freq = 3f;
        posology.setFreq(freq);
        assertEquals(posology.getFreq(),freq);
    }

    @Test
    public void TestsetFqUnit(){
        FqUnit frequence = FqUnit.HOUR;
        posology.setFreqUnit(frequence);

        assertEquals(posology.getFreqUnit(), frequence);
    }

}
