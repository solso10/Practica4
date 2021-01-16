package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import services.HealthNationalService;
import services.ScheduledVisitAgenda;

import java.util.Date;
import java.util.List;

public class ConsultationTerminal {

    private HealthNationalService hns;
    private ScheduledVisitAgenda visitAgenda;
    private HealthCardID hcID;
    private MedicalPrescription prescription;
    private ProductID pID;
//    private final DigitalSignature signature;
    private final Date today = new Date();
    private List<ProductSpecification> lastSearch;

//    public void initRevision() {
//
//    } throws HealthCardException,NotValidePrescriptionException, ConnectException;
//
//    public void initPrescriptionEdition() {
//
//    } throws AnyCurrentPrescriptionException, NotFinishedTreatmentException;
//
//    public void searchForProducts(String keyWord) {
//
//    } throws AnyKeyWordMedicineException, ConnectException;
//
//    public void selectProduct(int option) {
//
//    } throws AnyMedicineSearchException, ConnectException;
//
//    public void enterMedicineGuidelines(String[] instruc) {
//
//    } throws AnySelectedMedicineException, IncorrectTakingGuidelinesException;
//
//    public void enterTreatmentEndingDate(Date date) {
//    } throws IncorrectEndingDateException;
//
//    public void sendePrescription() {
//
//    } throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription;
//
//    public void printePresc() {
//
//    } throws printingException;
//  // Other methods, apart from the input events

//    public HealthCardID getHcID() {
//        return hcID;
//    }
//
//    public MedicalPrescription getMedPresc() {
//        return prescription;
//    }
//
//    public ProductID getpID() {
//        return pID;
//    }
//
//    public DigitalSignature getSign() {
//        return signature;
//    }
//
//    public List<ProductSpecification> getLastSearch() {
//        return lastSearch;
//    }
//
//    public void setSchedAgenda(ScheduledVisitAgenda visitAgenda) {
//        this.visitAgenda = visitAgenda;
//    }
//
//    public void setHns(HealthNationalService hns) {
//        this.hns = hns;
//    }
//
//    public void setHcID(HealthCardID hcID) {
//        this.hcID = hcID;
//    }
//
//    public void setMedPresc(MedicalPrescription prescription) {
//        this.prescription = prescription;
//    }
//
//    public void setLastSearch(List<ProductSpecification> lastSearch) {
//        this.lastSearch = lastSearch;
//    }
}
