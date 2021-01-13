package medicalconsultation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalPrescriptionLineTest {

    private  MedicalPrescriptionLine prescriptionline;

    @BeforeEach
    void setUp(){
        prescriptionline = new MedicalPrescriptionLine("1010115", dayMoment.DURINGDINNER, 4f, "medicamento para el dolor de cabeza",2f,4f, FqUnit.DAY);

    }

    @Test
    public void getProdId() {
        assertEquals(prescriptionline.getProdId(),"1010115");
        assertNotEquals(prescriptionline.getProdId(),"2002347");
    }

    @Test
    public void setProdId() {
        String productID =  "1010115";
        prescriptionline.setProdId(productID);
        assertEquals(prescriptionline.getProdId(),productID);
    }

    @Test
    public void getTguide() {
        TakingGuideline tkg1 = new TakingGuideline(dayMoment.DURINGDINNER,4f,"medicamento para el dolor de cabeza", 2f, 4f, FqUnit.DAY);
        TakingGuideline tkg2 = new TakingGuideline(dayMoment.DURINGBREAKFAST,6f,"medicamento para el dolor de estomago", 10f, 2f, FqUnit.MONTH);
        prescriptionline.setTguide(tkg1);

        assertEquals(prescriptionline.getTguide(), tkg1);
        assertNotEquals(prescriptionline.getTguide(), tkg2);
    }

    @Test
    public void setTguide() {
        TakingGuideline tkg =  new TakingGuideline(dayMoment.DURINGDINNER, 4f, "medicamento para el dolor de cabeza", 2f, 4f, FqUnit.DAY);
        prescriptionline.setTguide(tkg);
        assertEquals(prescriptionline.getTguide(),tkg);
    }

}