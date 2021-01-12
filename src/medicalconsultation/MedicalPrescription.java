package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;

import java.util.ArrayList;
import java.util.Date;

public class MedicalPrescription {

    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID;
    private DigitalSignature eSign;

    private int prodId;
    private ArrayList<MedicalPrescriptionLine> prescriptionLines;

    public MedicalPrescription(int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
    }


    public void addLine(ProductID prodID, String[] instruc)  {


    }
//
//    public void modifyLine(ProductID prodID, String[] instruc) { . . . }
//throws ProductNotInPrescription, IncorrectTakingGuidelinesException;
//
//    public void removeLine(ProductID prodID) { . . . } throws ProductNotInPrescription;
//


    public int getPrescCode() {
        return prescCode;
    }

    public void setPrescCode(int prescCode) {
        this.prescCode = prescCode;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public HealthCardID getHcID() {
        return hcID;
    }

    public void setHcID(HealthCardID hcID) {
        this.hcID = hcID;
    }

    public DigitalSignature geteSign() {
        return eSign;
    }

    public void seteSign(DigitalSignature eSign) {
        this.eSign = eSign;
    }
}
