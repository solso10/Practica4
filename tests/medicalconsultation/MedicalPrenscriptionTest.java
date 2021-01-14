package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MedicalPrenscriptionTest {

    private MedicalPrescription prescription;
    private TakingGuideline guideline;
    private HealthCardID hcID;
    private DigitalSignature signature;
    private ArrayList<MedicalPrescriptionLine> lines = new ArrayList<>();
    private MedicalPrescriptionLine mpline;
    private Object ProductID;


    @BeforeEach
    void setUp() {
        prescription = new MedicalPrescription(666, new Date(2021, 7, 2), new Date(2022, 6, 20), hcID, signature, lines);
    }


    @Test
    void getPresCode(){
        prescription.setPrescCode(666);

        assertEquals(666, prescription.getPrescCode());
        assertNotEquals(345, prescription.getPrescCode());
    }

    @Test
    void setPrescCode(){
        prescription.setPrescCode(765);

        assertEquals(765,prescription.getPrescCode());
        assertNotEquals(7,prescription.getPrescCode());
    }

    @Test
    void getPrescDate(){
        Date date1 = new Date(2021, 7, 2);
        Date date2 = new Date(2000, 5, 27);

        prescription.setPrescDate(date1);
        assertEquals(prescription.getPrescDate(), date1);
        assertNotEquals(prescription.getEndDate(), date2);
        prescription.setPrescDate(date2);
        assertEquals(prescription.getPrescDate(), date2);
        assertNotEquals(prescription.getEndDate(), date1);
    }

    @Test
    void setPrescDate(){
        Date date1 = new Date(2021, 7, 2);
        Date date2 = new Date(2000, 5, 27);

        prescription.setPrescDate(date1);
        assertEquals(prescription.getPrescDate(), date1);
        assertNotEquals(prescription.getEndDate(), date2);
    }

    @Test
    void getEndDate(){
        Date date1 = new Date(2022, 2, 21);
        Date date2 = new Date(2004, 8, 1);

        prescription.setEndDate(date1);

        assertEquals(prescription.getEndDate(), date1);
        assertNotEquals(prescription.getEndDate(), date2);
    }

    @Test
    void setEndDate(){
        Date date1 = new Date(2022, 2, 21);
        Date date2 = new Date(2004, 8, 1);

        prescription.setEndDate(date1);
        assertEquals(prescription.getEndDate(), date1);
        assertNotEquals(prescription.getEndDate(), date2);

        prescription.setEndDate(date2);
        assertEquals(prescription.getEndDate(), date2);
        assertNotEquals(prescription.getEndDate(), date1);
    }

    @Test
    void getHcID(){
        HealthCardID healthcard1 = new HealthCardID("ASDF1234567890");
        HealthCardID healthcard2 = new HealthCardID("QWER1234567890");

        prescription.setHcID(healthcard1);

        assertEquals(prescription.getHcID(), healthcard1);
        assertNotEquals(prescription.getHcID(), healthcard2);
    }

    @Test
    void setHcID(){
        HealthCardID healthcard1 = new HealthCardID("ASDF1234567890");
        HealthCardID healthcard2 = new HealthCardID("QWER1234567890");

        prescription.setHcID(healthcard1);

        assertEquals(prescription.getHcID(), healthcard1);
        assertNotEquals(prescription.getHcID(), healthcard2);

        prescription.setHcID(healthcard2);

        assertEquals(prescription.getHcID(), healthcard2);
        assertNotEquals(prescription.getHcID(), healthcard1);
    }

    @Test
    void seteSign(){
        DigitalSignature digSign1 = new DigitalSignature("DR".getBytes());
        DigitalSignature digSign2 = new DigitalSignature("RD".getBytes());

        prescription.seteSign(digSign1);

        assertEquals(prescription.geteSign(), digSign1);
        assertNotEquals(prescription.geteSign(), digSign2);

        prescription.seteSign(digSign2);

        assertEquals(prescription.geteSign(), digSign2);
        assertNotEquals(prescription.geteSign(), digSign1);
    }

    @Test
    void geteSign(){
        DigitalSignature digSign1 = new DigitalSignature("DR".getBytes());
        DigitalSignature digSign2 = new DigitalSignature("RD".getBytes());

        prescription.seteSign(digSign1);

        assertEquals(prescription.geteSign(), digSign1);
        assertNotEquals(prescription.geteSign(), digSign2);
    }


    @Test
    void setPrescriptionLinesTest() throws ProductIDException{

        ArrayList<MedicalPrescriptionLine> mpl1 = new ArrayList<>();
        MedicalPrescriptionLine mpl11 = new MedicalPrescriptionLine(new ProductID("1010115"), dayMoment.DURINGDINNER,4f,"medicamento para el colesterol", 2f, 4f, FqUnit.HOUR);
        mpl1.add(mpl11);
        ArrayList<MedicalPrescriptionLine> mpl2 = new ArrayList<>();
        MedicalPrescriptionLine mpl21 = new MedicalPrescriptionLine(new ProductID("2233445"), dayMoment.DURINGDINNER, 4f, "medicamento para el dolor de cabeza", 2f, 4f, FqUnit.DAY);
        mpl2.add(mpl21);

        prescription.setPrescriptionLines(mpl1);

        assertEquals(prescription.getPrescriptionLines(),mpl1);

        assertTrue(prescription.getPrescriptionLines().contains(mpl11));
        assertFalse(prescription.getPrescriptionLines().contains(mpl21));

        mpl1.add(mpl21);
        assertTrue(prescription.getPrescriptionLines().contains(mpl21));

        mpl2.add(mpl11);
        prescription.setPrescriptionLines(mpl2);
        assertEquals(prescription.getPrescriptionLines(),mpl2);

    }

    @Test
    void getPrescriptionLinesTest() throws ProductIDException {
        ArrayList<MedicalPrescriptionLine> mpl1 = new ArrayList<>();
        MedicalPrescriptionLine mpl11 = new MedicalPrescriptionLine(new ProductID("1010115"), dayMoment.DURINGDINNER,4f,"medicamento para el colesterol", 2f, 4f, FqUnit.HOUR);
        mpl1.add(mpl11);
        ArrayList<MedicalPrescriptionLine> mpl2 = new ArrayList<>();
        MedicalPrescriptionLine mpl21 = new MedicalPrescriptionLine(new ProductID("2233445"), dayMoment.DURINGDINNER, 4f, "medicamento para el dolor de cabeza", 2f, 4f, FqUnit.DAY);
        mpl2.add(mpl21);

        prescription.setPrescriptionLines(mpl1);

        assertEquals(prescription.getPrescriptionLines(),mpl1);
        assertNotEquals(prescription.getPrescriptionLines(),mpl2);
        assertTrue(prescription.getPrescriptionLines().contains(mpl11));
        assertFalse(prescription.getPrescriptionLines().contains(mpl21));
    }


    @Test
    public void addlinetest() throws ProductIDException, IncorrectTakingGuidelinesException {
        //dayMoment dM, float du, String i, float d ,float f, FqUnit u)

        MedicalPrescriptionLine mpl11 = new MedicalPrescriptionLine(new ProductID("1010115"), dayMoment.AFTERBREAKFAST,2f,"medicamento para  el dolor de cabeza", 2f, 4f, FqUnit.DAY);

        ProductID prodID = new ProductID("1010115");
        String[] array1 = new String[]{"AFTERBREAKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
        String[] array2 = new String[]{"1234", "","222","FA2","4AA",""};
        String[] array3 = new String[]{"A","44AA","A2QQ","QQW"};
        String[] array4 = new String[]{"AFTERBREAKFAST","2","4", "DAY"};
        String[] array5 = new String[]{"", "2","3","2","4", "DAY"};
        String[] array6 = new String[]{};

        prescription.addLine(prodID,array1);
        
        assertEquals(mpl11.getProdId(), prescription.getPrescriptionLines().get(0).getProdId());
        assertEquals(mpl11.getTguide().getInstructions(), prescription.getPrescriptionLines().get(0).getTguide().getInstructions());
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {prescription.addLine(prodID, array2);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {prescription.addLine(prodID, array3);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {prescription.addLine(prodID, array4);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {prescription.addLine(prodID, array5);});
        assertThrows(IncorrectTakingGuidelinesException.class, () -> {prescription.addLine(prodID, array6);});

    }

}

