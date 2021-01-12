package medicalconsultation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TakingGuidelineTest {

    public TakingGuideline guideline;

    @BeforeEach
    void setUp() {

        guideline = new TakingGuideline(dayMoment.AFTERMEALS, 2, "medicamento para la tos", 3,4, FqUnit.WEEK);

    }

    @Test
    public void TestgetdMoment(){
        assertEquals(guideline.getdMoment(),dayMoment.AFTERMEALS);
        assertNotEquals(guideline.getdMoment(),dayMoment.AFTERBREAKFAST);

    }

    @Test
    public void TestgetDuration(){
        assertEquals(guideline.getDuration(),2);
        assertNotEquals(guideline.getDuration(),4);
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
       Posology pos =  new Posology(4, 4, FqUnit.MONTH);
       guideline.setPosology(pos);
       assertEquals(guideline.getPosology(),pos);
    }

    @Test
    public void TestgetPosology(){
        Posology pos =  new Posology(4, 4, FqUnit.MONTH);
        guideline.setPosology(pos);
        assertEquals(guideline.getPosology(), pos);
        assertNotEquals(guideline.getPosology(), new Posology(2,6, FqUnit.DAY));
    }

}
