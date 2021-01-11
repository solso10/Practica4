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
      //  posology = new Posology(3,5,FqUnit.HOUR);
    }

    @Test
    public void getDose() {
        assertEquals(posology.getDose(), 7);
        assertNotEquals(posology.getDose(), 6);
       // assertEquals(posology.getDose(), 3);
        //assertNotEquals(posology.getDose(), 9);
    }

    @Test
    public void getFreq(){

        assertEquals(posology.getFreq(), 2);
        assertNotEquals(posology.getFreq(), 3);
        //assertEquals(posology.getFreq(), 5);
        //assertNotEquals(posology.getFreq(), 4);
    }

    @Test
    public void getFqUnit() {
        assertEquals(posology.getFreqUnit(), FqUnit.DAY);
        assertNotEquals(posology.getFreqUnit(), FqUnit.MONTH);
      //  assertEquals(posology.getFreqUnit(), FqUnit.HOUR);
       // assertNotEquals(posology.getFreqUnit(), FqUnit.DAY);

    }

    @Test
    public void setDose(){
        float dose = 3;
        //float d2 = 4;
        posology.setDose(dose);
        //posology.setDose(d2);
        assertEquals(posology.getDose(),dose);
        //assertEquals(posology.getDose(),d2);
    }

    @Test
    public void setFreg(){
        float dose = 3;
        posology.setFreq(dose);
        assertEquals(posology.getFreq(),dose);
    }

    @Test
    public void setFqUnit(){
        FqUnit frequence = FqUnit.HOUR;
        posology.setFreqUnit(frequence);

        assertEquals(posology.getFreqUnit(), frequence);
    }

}
