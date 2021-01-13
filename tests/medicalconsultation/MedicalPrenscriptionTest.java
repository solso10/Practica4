package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MedicalPrenscriptionTest {

    MedicalPrescription prescription;
    TakingGuideline guideline;
    private HealthCardID hcID;
    private DigitalSignature sign;
    private ArrayList<MedicalPrescriptionLine> lines;
    @BeforeEach
    void setUp() {
        prescription = new MedicalPrescription(666, new Date(2021, 7, 2), new Date(2022, 6, 20), hcID, sign, lines);
        //prescription = new MedicalPrescription(new ProductID("1010115"),
       // guideline = new TakingGuideline(dayMoment.DURINGDINNER, 4f, "medicamento para el dolor de cabeza",2f,4f, FqUnit.DAY);
    }

    @Test
    void getPresCode(){
        assertEquals(666, prescription.getPrescCode());
        assertNotEquals(345, prescription.getPrescCode());
    }

    @Test
    void setPrescCode(){
        prescription.setPrescCode(765);
        assertEquals(765,prescription.getPrescCode());
    }

    @Test
    void getPrescDate(){
        assertEquals(new Date(2021, 7, 2), prescription.getPrescDate());
        assertNotEquals(new Date(2000, 5, 27), prescription.getPrescDate());
    }

    @Test
    void setPrescDate(){
        Date firstDate = new Date(2021, 7, 2);
        prescription.setPrescDate(firstDate);
        assertEquals(firstDate, prescription.getPrescCode());
    }

    @Test
    void getEndDate(){
        assertEquals(new Date(2022, 6,20),prescription.getEndDate());
        assertNotEquals(new Date(2023, 8,30), prescription.getEndDate());
    }

    @Test
    void setEndDate(){
        Date finalDate = new Date(2022, 6, 20);
        prescription.setEndDate(finalDate);
        assertEquals(finalDate, prescription.getEndDate());
    }

    @Test
    void getHcID(){

    }

  /*  @Test
    public void addlinetest() throws ProductIDException, IncorrectTakingGuidelinesException {
        //dayMoment dM, float du, String i, float d ,float f, FqUnit u)
         ProductID prodID = new ProductID("1010115");
         String[] array1 = new String[]{"AFTERBREACKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
         String[] array2 = new String[]{"1234", "","222","FA2","4AA",""};
         String[] array3 = new String[]{"A","44AA","A2QQ","QQW"};
         String[] array4 = new String[]{"AFTERBREACKFAST","2","4", "DAY"};
         String[] array5 = new String[]{"", "2",,"2","4", "DAY"};

         prescription.addLine(prodID,array1);
         prescription.addLine(prodID,array2);
         prescription.addLine(prodID,array3);
         prescription.addLine(prodID,array4);
         prescription.addLine(prodID,array5);

        assertEquals(prescription, prescription.addLine(ProductID);    }
*/
}

