package services;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HealthNationalServiceTest {
    private HealthNationalServiceClass hnService;

    private HealthCardID hcdi1;
    private DigitalSignature ds;
    private MedicalPrescription mp1;
    private List<ProductSpecification> pd1;
    private List<ProductSpecification> pd2;

    @BeforeEach
    void setUp() throws Exception, IncorrectTakingGuidelinesException {

        hnService = new HealthNationalServiceClass();

        hcdi1 = new HealthCardID("ABCD1234567890");
        ds = new DigitalSignature("DR".getBytes());
        mp1 = new MedicalPrescription(1234, new Date(2020,12,20), new Date(2020,12,30), hcdi1, ds);
        ProductID prodID1 = new ProductID("1010115");
        ProductID prodID2 = new ProductID("1010116");
        String[] array1 = new String[]{"AFTERBREAKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
        String[] array2 = new String[]{"BEFOREBREAKFAST", "3","medicamento para  el dolor de pecho","3","6", "HOUR"};
        mp1.addLine(prodID1,array1);
        mp1.addLine(prodID2,array2);


        String desc1 = "Ingerir via oral";
        String desc2 = "Aplicar de forma cutanea";

        ProductSpecification ps1 = new ProductSpecification(prodID1, desc1, new BigDecimal(10.0));
        ProductSpecification ps2 = new ProductSpecification(prodID2, desc2, new BigDecimal(4.5));

        pd1 = new ArrayList<>(Arrays.asList(ps1, ps2));
        pd2 = new ArrayList<>(Arrays.asList(ps2, ps1));

    }

    @Test
    void getePrescriptionTest() throws NotValidePrescriptionException, HealthCardException, ConnectException {
        assertEquals(hnService.getePrescription(hcdi1).getEndDate(), mp1.getEndDate());
        assertEquals(hnService.getePrescription(hcdi1).geteSign(), mp1.geteSign());
        assertEquals(hnService.getePrescription(hcdi1).getHcID(), mp1.getHcID());
        assertEquals(hnService.getePrescription(hcdi1).getPrescDate(), mp1.getPrescDate());
        assertEquals(hnService.getePrescription(hcdi1).getPrescCode(), mp1.getPrescCode());

        for(int i = 0; i<mp1.getPrescriptionLines().size(); i++){
            assertEquals(mp1.getPrescriptionLines().get(i).getProdId(), hnService.getePrescription(hcdi1).getPrescriptionLines().get(i).getProdId());
        }

    }

    @Test
    void getProductsByKW() throws AnyKeyWordMedicineException, ConnectException{
        for(int i = 0; i<pd1.size(); i++){
            assertEquals(hnService.getProductsByKW("Medicamento 1").get(0).getUPCcode(), pd1.get(0).getUPCcode());
            assertEquals(hnService.getProductsByKW("Medicamento 1").get(0).getDescription(), pd1.get(0).getDescription());
            assertEquals(hnService.getProductsByKW("Medicamento 1").get(0).getPrice(), pd1.get(0).getPrice());
            assertNotEquals(hnService.getProductsByKW("Medicamento 2").get(0).getUPCcode(), pd1.get(0).getUPCcode());
        }

        assertThrows(AnyKeyWordMedicineException.class, () -> {hnService.getProductsByKW(null);});
        assertThrows(AnyKeyWordMedicineException.class, () -> {hnService.getProductsByKW("");});
    }

    @Test
    void getProductSpecific() throws AnyKeyWordMedicineException, AnyMedicineSearchException, ConnectException{
        ProductSpecification pdd = hnService.getProductsByKW("Medicamento 1").get(0);
        assertEquals(pd1.get(1).getUPCcode(), hnService.getProductSpecific(1).getUPCcode());
        assertEquals(pdd.getUPCcode(), hnService.getProductSpecific(0).getUPCcode());
        assertNotEquals(pd1.get(0).getUPCcode(), hnService.getProductSpecific(1).getUPCcode());

        assertThrows(AnyMedicineSearchException.class, () -> {hnService.getProductSpecific(9999);});

    }

    @Test
    void sendePrescription() throws ProductIDException, IncorrectTakingGuidelinesException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription, ConnectException, NotValidePrescriptionException, HealthCardException{

        HealthCardID hcdi1 = new HealthCardID("ABCD1234567890");
        HealthCardID hcdi2 = new HealthCardID("EFGH2345678901");
        HealthCardID hcdi3 = new HealthCardID("IJKL3456789012");

        DigitalSignature ds = new DigitalSignature("DR".getBytes());

        MedicalPrescription mp1 = new MedicalPrescription(1234, new Date(2020,12,20), new Date(2020,12,30), hcdi1, ds);
        MedicalPrescription mp2 = new MedicalPrescription(5678, null, null, hcdi2, ds);
        MedicalPrescription mp3 = new MedicalPrescription(5678, new Date(2020,12,20), new Date(2020,12,30), null, ds);
        MedicalPrescription mp4 = new MedicalPrescription(5678, new Date(2020,12,20), new Date(2020,12,30), hcdi3, null);

        ProductID prodID1 = new ProductID("1010115");
        ProductID prodID2 = new ProductID("1010116");
        ProductID prodID3 = new ProductID("1010117");
        ProductID prodID4 = new ProductID("1010118");

        String[] array1 = new String[]{"AFTERBREAKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
        String[] array2 = new String[]{"BEFOREBREAKFAST", "3","medicamento para  el dolor de pecho","3","6", "HOUR"};
        String[] array3 = new String[]{"DURINGBREAKFAST", "4","medicamento para  el dolor de corazon","4","5", "WEEK"};
        String[] array4 = new String[]{"AFTERDINNER", "5","medicamento para  el dolor de pie","7","8", "WEEK"};

        mp3.addLine(prodID1,array1);
        mp4.addLine(prodID2,array2);
        mp2.addLine(prodID3,array3);
        mp2.addLine(prodID4,array4);

        assertThrows(NotValidePrescription.class, () -> {hnService.sendePrescription(mp1);});
        assertThrows(eSignatureException.class, () -> {hnService.sendePrescription(mp4);});
        assertThrows(NotCompletedMedicalPrescription.class, () -> {hnService.sendePrescription(mp2);});
        assertThrows(NotCompletedMedicalPrescription.class, () -> {hnService.sendePrescription(mp2);});
        assertThrows(NotCompletedMedicalPrescription.class, () -> {hnService.sendePrescription(mp3);});

        mp1.addLine(prodID1,array1);
        mp1.addLine(prodID2,array2);

        MedicalPrescription mp5 = hnService.getePrescription(hcdi1);

        assertEquals(mp5, hnService.sendePrescription(mp5));


    }

}
