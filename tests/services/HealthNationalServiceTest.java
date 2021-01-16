package services;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HealthNationalServiceTest {
    private HealthNationalServiceClass hnService;
    private HealthCardID hcdi1,hcdi2;
    private DigitalSignature ds;
    private MedicalPrescription mp1, mp2;

    @BeforeEach
    void setUp() throws Exception, IncorrectTakingGuidelinesException {

        hnService = new HealthNationalServiceClass();

        hcdi1 = new HealthCardID("ABCD1234567890");
        hcdi2 = new HealthCardID("EFGH2345678901");

        ds = new DigitalSignature("DR".getBytes());

        mp1 = new MedicalPrescription(1234, new Date(2020,12,20), new Date(2020,12,30), hcdi1, ds);
        mp2 = new MedicalPrescription(5678, new Date(2020,10,10), new Date(2020,11,20), hcdi2, ds);

        ProductID prodID1 = new ProductID("1010115");
        ProductID prodID2 = new ProductID("1010116");

        String[] array1 = new String[]{"AFTERBREAKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
        String[] array2 = new String[]{"BEFOREBREAKFAST", "3","medicamento para  el dolor de pecho","3","6", "HOUR"};

        //mp1.addLine(prodID1,array1);
        //mp1.addLine(prodID2,array2);

    }

    @Test
    void getePrescriptionTest() throws NotValidePrescriptionException, HealthCardException, ConnectException, ProductIDException {
        assertEquals(hnService.getePrescription(hcdi1),new HealthCardID("ABCD1234567890"));

        //assertNotEquals(mp1,hnService.getePrescription(hcdi1));
    }

}
