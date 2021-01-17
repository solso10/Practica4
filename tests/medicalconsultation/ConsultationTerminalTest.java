package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.HealthNationalServiceClass;
import services.ScheduledVisitAgendaClass;


import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultationTerminalTest {

    private HealthCardID hcID;
    private ConsultationTerminal consultation;
    private DigitalSignature signature;
    private HealthNationalServiceClass HNS;
    private ScheduledVisitAgendaClass visitAgenda;
    private MedicalPrescription prescription;
    private String[] instruct, instruct2;
    private Date datafinal, datafinal2;


    @BeforeEach
    void setUp() throws IncorrectTakingGuidelinesException, Exception {
        signature = new DigitalSignature("DR".getBytes());
        consultation = new ConsultationTerminal(signature);
        HNS = new HealthNationalServiceClass();
        visitAgenda = new ScheduledVisitAgendaClass();
        consultation.setHcID(hcID);
        consultation.setSchedAgenda(visitAgenda);
        hcID = new HealthCardID("ABCD1234567890");

        prescription = new MedicalPrescription(1234, new Date(2020,12,20), new Date(2020,12,30), hcID, signature);
        instruct = new String[] {"AFTERBREAKFAST","7","abc","5","4","DAY"};
        instruct2 = new String[] {};
        datafinal = new Date(2023,4,28);
        datafinal2 = new Date(2020,4,28);
    }

    @Test
    void initRevisonTest() throws Exception, IncorrectTakingGuidelinesException {
        consultation.initRevision();

        assertEquals(hcID,consultation.getHcID());
        assertNotEquals(prescription,consultation.getMedPresc());

        ProductID prodID1 = new ProductID("1010115");
        ProductID prodID2 = new ProductID("1010116");

        String[] array1 = new String[]{"AFTERBREAKFAST", "2","medicamento para  el dolor de cabeza","2","4", "DAY"};
        String[] array2 = new String[]{"BEFOREBREAKFAST", "3","medicamento para  el dolor de pecho","3","6", "HOUR"};
        prescription.addLine(prodID1,array1);
        prescription.addLine(prodID2,array2);

        assertEquals(prescription.getHcID(), consultation.getMedPresc().getHcID());
        assertEquals(prescription.getPrescriptionLines().size(), consultation.getMedPresc().getPrescriptionLines().size());

        for(int i = 0; i<prescription.getPrescriptionLines().size(); i++){
            assertEquals(prescription.getPrescriptionLines().get(i).getProdId(), consultation.getMedPresc().getPrescriptionLines().get(i).getProdId());
        }
    }

    @Test
    void initPrescriptionEditionTest(){

        assertThrows(AnyCurrentPrescriptionException.class, () -> {consultation.initPrescriptionEdition();});
        consultation.setMedPresc(prescription);
        assertThrows(NotFinishedTreatmentException.class, () -> {consultation.initPrescriptionEdition();});
    }

    @Test
    void searchForProductsTest() throws Exception {


        assertThrows(AnyKeyWordMedicineException.class, () -> { consultation.searchForProducts(""); });
        assertThrows(AnyKeyWordMedicineException.class, () -> { consultation.searchForProducts(null); });

        consultation.searchForProducts("Medicamento 1");

        ProductSpecification ps1 = new ProductSpecification(new ProductID("1010115"), "Ingerir via oral", new BigDecimal(10.0));
        ProductSpecification ps2 = new ProductSpecification(new ProductID("1010116"), "Aplicar de forma cutanea", new BigDecimal(4.5));


        assertEquals(consultation.getLastSearch().get(0).getUPCcode(), ps1.getUPCcode());
        assertEquals(consultation.getLastSearch().get(1).getUPCcode(), ps2.getUPCcode());
    }
    @Test
    void selectProductTest() throws HealthCardException, ConnectException, NotValidePrescriptionException, java.net.ConnectException, AnyKeyWordMedicineException, AnyMedicineSearchException, ProductIDException {

        consultation.initRevision();
        consultation.searchForProducts("Medicamento 2");
        consultation.selectProduct(1);
        assertEquals(consultation.getpID(),new ProductID("1010115"));
        assertNotEquals(consultation.getpID(),new ProductID("0000000"));

        assertThrows(ConnectException.class,() -> {consultation.selectProduct(-1);});
    }

    @Test
    void enterMedicineGuidelinesTest() throws java.net.ConnectException, AnyMedicineSearchException, ConnectException, AnyKeyWordMedicineException, ProductIDException, IncorrectTakingGuidelinesException, AnySelectedMedicineException, ProductNotInPrescription, HealthCardException, NotValidePrescriptionException {

        assertThrows(AnySelectedMedicineException.class, () -> { consultation.enterMedicineGuidelines(null);});

        ProductID prodID1 = new ProductID("1010115");
        ProductID prodID2 = new ProductID("1010115");
        ProductID prodID3 = new ProductID("1010115");
        consultation.initRevision();
        consultation.searchForProducts("Medicamento 2");
        consultation.selectProduct(1);
        consultation.enterMedicineGuidelines(instruct);

        prescription.addLine(prodID1, instruct);
        prescription.addLine(prodID2,instruct);
        prescription.addLine(prodID3,instruct);

        System.out.println(prescription.getPrescriptionLines().size());
        System.out.println(consultation.getMedPresc().getPrescriptionLines().size());

        assertNotEquals(consultation.getMedPresc(), prescription);

    }

    @Test
    void enterTreatmentEndingDateTest() throws HealthCardException, ConnectException, NotValidePrescriptionException, java.net.ConnectException, AnyKeyWordMedicineException, AnySelectedMedicineException, IncorrectTakingGuidelinesException, AnyMedicineSearchException, IncorrectEndingDateException, NotCompletedMedicalPrescription, NotValidePrescription, eSignatureException {

        consultation.initRevision();
        consultation.searchForProducts("Medicamento 2");
        consultation.selectProduct(1);
        consultation.enterMedicineGuidelines(instruct);
        consultation.enterTreatmentEndingDate(datafinal);
        consultation.sendePrescription();

        assertEquals(consultation.getMedPresc().getEndDate(),datafinal);
        assertNotEquals(consultation.getMedPresc().getEndDate(),datafinal2);

    }

    @Test
    void sendePrescriptionTest() throws HealthCardException, ConnectException, NotValidePrescriptionException, java.net.ConnectException, AnyKeyWordMedicineException, AnyMedicineSearchException, AnySelectedMedicineException, IncorrectTakingGuidelinesException, IncorrectEndingDateException {

        assertThrows(NotValidePrescription.class, () -> {consultation.sendePrescription();});

        signature = new DigitalSignature("DR".getBytes());
        consultation.initRevision();
        consultation.searchForProducts("Medicamento 2");
        consultation.selectProduct(1);
        consultation.enterMedicineGuidelines(instruct);
        consultation.enterTreatmentEndingDate(datafinal);

        assertEquals(consultation.getMedPresc().geteSign(),signature);

    }
}
