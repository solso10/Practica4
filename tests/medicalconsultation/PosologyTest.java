package medicalconsultation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PosologyTest {

    public Posology posology;

    @BeforeEach
    void setUp() {

        posology = new Posology(7,2,FqUnit.DAY);
    }

    @Test
    public void TestgetDose() {
        assertEquals(posology.getDose(), 7);
        assertNotEquals(posology.getDose(), 6);

    }

    @Test
    public void TestgetFreq(){

        assertEquals(posology.getFreq(), 2);
        assertNotEquals(posology.getFreq(), 3);

    }

    @Test
    public void TestgetFqUnit() {
        assertEquals(posology.getFreqUnit(), FqUnit.DAY);
        assertNotEquals(posology.getFreqUnit(), FqUnit.MONTH);


    }

    @Test
    public void TestsetDose(){
        float dose = 3;
        posology.setDose(dose);
        assertEquals(posology.getDose(),dose);
    }

    @Test
    public void TestsetFreg(){
        float freq = 3;
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
