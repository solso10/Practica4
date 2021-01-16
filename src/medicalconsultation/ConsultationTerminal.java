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

    private HealthNationalService HNS;
    private ScheduledVisitAgenda visitAgenda;
    private HealthCardID hcID;
    private MedicalPrescription prescription;
    private ProductID product;
    private final DigitalSignature signature;
    private final Date today = new Date();
    private List<ProductSpecification> search;
    private ProductSpecification specification;


    public ConsultationTerminal(DigitalSignature signature) {
        this.signature = signature;
    }

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException, java.net.ConnectException {
        hcID = visitAgenda.getHealthCardID();
        if (hcID == null)  throw new HealthCardException("identificacion incorrecta");
        prescription = HNS.getePrescription(hcID);
        if (prescription == null) throw new NotValidePrescriptionException("prescripcion no valida");
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        if (prescription.getEndDate().before(today)) throw new NotFinishedTreatmentException("Tratamineto no terminado");
        if (prescription == null ) throw new AnyCurrentPrescriptionException("No hay prescripcion");
    }

    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException, java.net.ConnectException {
        search = HNS.getProductsByKW(keyWord);
        if (search == null) throw new AnyKeyWordMedicineException("no hay ninguna keyWord");

    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException, java.net.ConnectException {
        specification = HNS.getProductSpecific(option);
        if (specification == null ) throw new AnyMedicineSearchException("No hay ningun medicamento");
        product = specification.getUPCcode();

    }

    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException {
        if (prescription == null) throw new AnySelectedMedicineException("No hay prescripcion");
        prescription.addLine(product,instruc);
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException {

        if(date.before(today)) throw new IncorrectEndingDateException("Fecha incorrecta");
        prescription.setEndDate(new Date(2023, 4,28));
    }

    public void sendePrescription() throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription, java.net.ConnectException {

        if(prescription == null) throw new NotValidePrescription("No hay prescripcion");
        prescription.seteSign(signature);
        prescription = HNS.sendePrescription(prescription);

    }

    public void printePresc() throws printingException {
        if(prescription == null) throw new printingException("No hay nada que imprimir");
        System.out.println(prescription.toString());
    }


    public HealthCardID getHcID() {
        return hcID;
    }

    public MedicalPrescription getMedPresc() {
        return prescription;
    }

    public ProductID getpID() {
        return product;
    }

    public DigitalSignature getSign() {
        return signature;
    }

    public List<ProductSpecification> getLastSearch() {
        return search;
    }

    public void setSchedAgenda(ScheduledVisitAgenda visitAgenda) {
        this.visitAgenda = visitAgenda;
    }

    public void setHns(HealthNationalService hns) {
        this.HNS = hns;
    }

    public void setHcID(HealthCardID hcID) {
        this.hcID = hcID;
    }

    public void setMedPresc(MedicalPrescription prescription) {
        this.prescription = prescription;
    }

    public void setLastSearch(List<ProductSpecification> lastSearch) {
        this.search = lastSearch;
    }
}
