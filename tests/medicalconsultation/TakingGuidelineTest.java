package medicalconsultation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TakingGuidelineTest {

    public TakingGuideline guideline;

    @BeforeEach
    void setUp() {

        guideline = new TakingGuideline(dayMoment.AFTERMEALS, 2f, "medicamento para la tos", 4f,4f, FqUnit.WEEK);

    }

    @Test
    public void TestgetdMoment(){
        assertEquals(guideline.getdMoment(),dayMoment.AFTERMEALS);
        assertNotEquals(guideline.getdMoment(),dayMoment.AFTERBREAKFAST);

    }

    @Test
    public void TestgetDuration(){
        assertEquals(guideline.getDuration(),2f);
        assertNotEquals(guideline.getDuration(),4f);
    }


    @Test
    public void TestgetInstruccion(){
        assertEquals(guideline.getInstructions(),"medicamento para la tos");
        assertNotEquals(guideline.getInstructions(),"medicamento para el resfriado");
    }


    @Test
    public void TestsetdMoment(){
        dayMoment moment = dayMoment.AFTERLUNCH;
        guideline.setdMoment(moment);
    }

    @Test
    public void TestsetDuration(){
        float durat = 3;
        guideline.setDuration(durat);
        assertEquals(guideline.getDuration(),durat);
    }

    @Test
    public void TestsetInstructions(){
        String instruction =  "medicamento para la tos";
        guideline.setInstructions(instruction);
        assertEquals(guideline.getInstructions(),instruction);
    }

    @Test
    public void TestsetPosology(){
       Posology pos =  new Posology(4f, 4f, FqUnit.MONTH);
       guideline.setPosology(pos);
       assertEquals(guideline.getPosology(),pos);
    }

    @Test
    public void TestgetPosology(){
        Posology pos1 = new Posology(4f, 4f, FqUnit.MONTH);
        Posology pos2 =new Posology(2f,6f, FqUnit.DAY);

        guideline.setPosology(pos1);

        assertEquals(guideline.getPosology(), pos1);
        assertNotEquals(guideline.getPosology(), pos2);
    }

}
