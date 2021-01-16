package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.HealthNationalServiceClass;
import services.ScheduledVisitAgendaClass;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    void setUp(){
        consultation = new ConsultationTerminal(new DigitalSignature("DOCTOR".getBytes()));
        consultation.setHcID(hcID);
        consultation.setSchedAgenda(visitAgenda);
        hcID = new HealthCardID("ABCD1234567890");

        prescription = new MedicalPrescription(123, new Date(2020, 2,28), new Date(2021,2,29),hcID, signature);
        instruct = new String[] {"AFTERBREAKFAST","7","abc","5","4","DAY"};
    }

    @Test
    void initRevisonTest() throws HealthCardException, ConnectException, NotValidePrescriptionException, java.net.ConnectException {
        consultation.initRevision();
        assertEquals(hcID,consultation.getHcID());
        assertEquals(prescription,consultation.getMedPresc());


    }

    @Test
    void initPrescriptionEdition(){

        assertThrows(AnyCurrentPrescriptionException.class, () -> {consultation.initPrescriptionEdition();});
        assertThrows(NotFinishedTreatmentException.class, () -> {consultation.initPrescriptionEdition();});
    }

    @Test
    void searchForProducts(){


    }

    @Test
    void enterMedicineGuidelines() throws java.net.ConnectException, AnyMedicineSearchException, ConnectException, AnyKeyWordMedicineException, ProductIDException, IncorrectTakingGuidelinesException, AnySelectedMedicineException, ProductNotInPrescription {
        assertThrows(AnySelectedMedicineException.class, () -> { consultation.enterMedicineGuidelines(null); });

        consultation.searchForProducts("paracetamol");
        consultation.selectProduct(1);
        consultation.enterMedicineGuidelines(instruct);

        prescription.addLine(new ProductID("000736484763"), instruct);

        assertEquals(consultation.getMedPresc(), prescription);
        prescription.removeLine(new ProductID("000736484763"));
    }
}
