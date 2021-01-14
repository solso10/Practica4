package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.IncorrectTakingGuidelinesException;
import exceptions.ProductNotInPrescription;

import java.util.ArrayList;
import java.util.Date;

public class MedicalPrescription {

    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID;
    private DigitalSignature eSign;
    private ArrayList<MedicalPrescriptionLine> prescriptionLines;

    public MedicalPrescription(int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
    }

    public MedicalPrescription(HealthCardID hcID) {
        this.hcID = hcID;
        this.prescriptionLines = new ArrayList<>();
    }

    public MedicalPrescription(int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign, ArrayList<MedicalPrescriptionLine> lines) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.prescriptionLines = lines;
    }


    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException{
        if (instruc.length != 6 || instruc[0] == null || instruc[1] == null || instruc[2] == null || instruc[3] == null || instruc[4] == null){
            throw new IncorrectTakingGuidelinesException("Linea mal escrita");
        }
        //prescriptionLines(String prodId, dayMoment dM, float du, String i, float d ,float f, FqUnit u)

        try{
            dayMoment dM = dayMoment.valueOf(instruc[0]);
            float du = Float.parseFloat(instruc[1]);
            float d = Float.parseFloat(instruc[3]);
            float f = Float.parseFloat(instruc[4]);
            FqUnit u = FqUnit.valueOf(instruc[5]);
            prescriptionLines.add(new MedicalPrescriptionLine(prodID,dM, du, instruc[2], d, f, u));

        }catch (Exception e){
            throw new IncorrectTakingGuidelinesException("");
        }
   }


    public void modifyLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {


    }





    public void removeLine(ProductID prodID) throws ProductNotInPrescription {

    }



    public int getPrescCode() { return prescCode; }

    public void setPrescCode(int prescCode) { this.prescCode = prescCode; }

    public Date getPrescDate() { return prescDate; }

    public void setPrescDate(Date prescDate) { this.prescDate = prescDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public HealthCardID getHcID() { return hcID; }

    public void setHcID(HealthCardID hcID) { this.hcID = hcID; }

    public DigitalSignature geteSign() { return eSign; }

    public void seteSign(DigitalSignature eSign) { this.eSign = eSign; }

    public ArrayList<MedicalPrescriptionLine> getPrescriptionLines() { return prescriptionLines; }

    public void setPrescriptionLines(ArrayList<MedicalPrescriptionLine> prescriptionLines) { this.prescriptionLines = prescriptionLines; }
}
