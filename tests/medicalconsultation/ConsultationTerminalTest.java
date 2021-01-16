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
    private ProductID product;
    private TakingGuideline tkg;
    private ConsultationTerminal consultation;
    private DigitalSignature signature;
    private HealthNationalServiceClass HNS;
    private ScheduledVisitAgendaClass visitAgenda;
    private MedicalPrescription prescription;
    private String[] instruct;


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

    }

    @Test
    void initPrescriptionEditionTest(){

        assertThrows(AnyCurrentPrescriptionException.class, () -> {consultation.initPrescriptionEdition();});
        consultation.setMedPresc(prescription);
        assertThrows(NotFinishedTreatmentException.class, () -> {consultation.initPrescriptionEdition();});
    }

    @Test
    void searchForProductsTest() throws ConnectException, java.net.ConnectException, AnyKeyWordMedicineException, ProductIDException {

        consultation.searchForProducts("Medicamento 1");
        assertThrows(AnyKeyWordMedicineException.class, () -> { consultation.searchForProducts(""); });
        assertThrows(AnyKeyWordMedicineException.class, () -> { consultation.searchForProducts(null); });

        //ProductSpecification ps = new ProductSpecification(new ProductID("1010115"), "Ingerir via oral", new BigDecimal(10.0));
        //Falta fer un assert equals
    }

    @Test
    void enterMedicineGuidelinesTest() throws java.net.ConnectException, AnyMedicineSearchException, ConnectException, AnyKeyWordMedicineException, ProductIDException, IncorrectTakingGuidelinesException, AnySelectedMedicineException, ProductNotInPrescription {
        assertThrows(AnySelectedMedicineException.class, () -> { consultation.enterMedicineGuidelines(null); });

        consultation.searchForProducts("paracetamol");
        consultation.selectProduct(1);
        consultation.enterMedicineGuidelines(instruct);

        prescription.addLine(new ProductID("000736484763"), instruct);

        assertEquals(consultation.getMedPresc(), prescription);
        prescription.removeLine(new ProductID("000736484763"));
    }
}
