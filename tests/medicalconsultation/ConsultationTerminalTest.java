package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.HealthNationalServiceClass;
import services.ScheduledVisitAgendaClass;


import java.util.Calendar;
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
    void setUp(){
       /* consultation = new ConsultationTerminal(new DigitalSignature("DOCTOR".getBytes()));
        consultation.setHcID(hcID);
        consultation.setSchedAgenda(visitAgenda);
        hcID = new HealthCardID("ABCD1234567890");

        prescription = new MedicalPrescription(123, new Date(2020, 2,28), new Date(2021,2,29),hcID, signature);
        instruct = new String[] {"AFTERBREAKFAST","7","abc","5","4","DAY"};*/
    }

    @Test
    void initRevisonTest() throws HealthCardException, ConnectException, NotValidePrescriptionException, java.net.ConnectException {

        consultation = new ConsultationTerminal(new DigitalSignature("DOCTOR".getBytes()));
        hcID = new HealthCardID("ABCD1234567890");
        prescription = new MedicalPrescription(123, new Date(2020, 2,28), new Date(2021,2,29),hcID, signature);

        consultation.initRevision();
        assertEquals(consultation.getHcID(),hcID);
        assertEquals(prescription,consultation.getMedPresc());





        /*HealthCardID hcID = new HealthCardID("ABCD1234567890");
        consultation.setHcID(hcID);
        assertEquals(consultation.getHcID(),hcID);*/




      /* consultation.initRevision();
        assertEquals(hcID,consultation.getHcID());
        assertEquals(prescription,consultation.getMedPresc());*/

     /*   TakingGuideline tkg1 = new TakingGuideline(dayMoment.DURINGDINNER,4f,"medicamento para el dolor de cabeza", 2f, 4f, FqUnit.DAY);
        TakingGuideline tkg2 = new TakingGuideline(dayMoment.DURINGBREAKFAST,6f,"medicamento para el dolor de estomago", 10f, 2f, FqUnit.MONTH);
        prescriptionline.setTguide(tkg1);

        assertEquals(prescriptionline.getTguide(), tkg1);
        assertNotEquals(prescriptionline.getTguide(), tkg2);*/
    }

    @Test
    void initPrescriptionEditionTest(){

        assertThrows(AnyCurrentPrescriptionException.class, () -> {consultation.initPrescriptionEdition();});
        assertThrows(NotFinishedTreatmentException.class, () -> {consultation.initPrescriptionEdition();});
    }

    @Test
    void searchForProductsTest(){


    }

    @Test
    void selectProduct(){

    }

    @Test
    void enterMedicineGuidelinesTest() throws java.net.ConnectException, AnyMedicineSearchException, ConnectException, AnyKeyWordMedicineException, ProductIDException, IncorrectTakingGuidelinesException, AnySelectedMedicineException, ProductNotInPrescription {

        assertThrows(AnySelectedMedicineException.class, () -> { consultation.enterMedicineGuidelines(null); });

        consultation.searchForProducts("paracetamol");
      /*  consultation.selectProduct(1);
        consultation.enterMedicineGuidelines(instruct);

        prescription.addLine(new ProductID("000736484763"), instruct);

        assertEquals(consultation.getMedPresc(), prescription);
        prescription.removeLine(new ProductID("000736484763"));*/
    }

    @Test
    void enterTreatmentEndingDateTest(){
        assertThrows(IncorrectEndingDateException.class, () -> {consultation.enterTreatmentEndingDate(new Date(2019-1900, Calendar.DECEMBER, 15));});
        Date end = new Date(2023, 4, 28);

        assertEquals(end, consultation.getMedPresc().getEndDate());
    }


}
