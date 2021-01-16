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
    private ArrayList<MedicalPrescriptionLine> prescriptionLines = new ArrayList<>();

    public MedicalPrescription(int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign) {
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
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
        if (instruc.length != 6 || instruc[0] == null || instruc[1] == null || instruc[2] == null || instruc[3] == null || instruc[4] == null || instruc[5] == null){
            throw new IncorrectTakingGuidelinesException("Linea mal escrita");
        }

        if (instruc[0].isEmpty() || instruc[1].isEmpty()|| instruc[2].isEmpty()|| instruc[3].isEmpty()|| instruc[4].isEmpty() || instruc[5].isEmpty()){
            throw new IncorrectTakingGuidelinesException("Linea mal escrita");
        }

        try{

            dayMoment dM = dayMoment.getdayMoment(instruc[0]);
            float du = Float.parseFloat(instruc[1]);
            float d = Float.parseFloat(instruc[3]);
            float f = Float.parseFloat(instruc[4]);
            FqUnit u = FqUnit.getFqUnit(instruc[5]);

            prescriptionLines.add(new MedicalPrescriptionLine(prodID, dM, du, instruc[2], d, f, u));

        }catch (Exception e){
            throw new IncorrectTakingGuidelinesException("Error11");
        }
   }


    public void modifyLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {

        if (instruc.length != 6 || instruc[0] == null || instruc[1] == null || instruc[2] == null || instruc[3] == null || instruc[4] == null || instruc[5] == null){
            throw new IncorrectTakingGuidelinesException("Linea mal escrita");
        }

        dayMoment dM;
        String msg;
        float du, d, f;
        FqUnit u;

        try {

            for(int i = 0; i < prescriptionLines.size(); i++){
                if(prescriptionLines.get(i).getProdId().equals(prodID)){
                    if(!instruc[0].isEmpty()){
                        dM = dayMoment.getdayMoment(instruc[0]);
                    } else {
                        dM = prescriptionLines.get(i).getTguide().getdMoment();
                    }

                    if(!instruc[1].isEmpty()){
                        du = Float.parseFloat(instruc[1]);
                    } else {
                        du = prescriptionLines.get(i).getTguide().getDuration();
                    }

                    if(!instruc[2].isEmpty()){
                        msg = instruc[2];
                    } else {
                        msg = prescriptionLines.get(i).getTguide().getInstructions();
                    }

                    if(!instruc[3].isEmpty()){
                        d = Float.parseFloat(instruc[3]);
                    } else {
                        d = prescriptionLines.get(i).getTguide().getPosology().getDose();
                    }

                    if(!instruc[4].isEmpty()){
                        f = Float.parseFloat(instruc[4]);
                    } else {
                        f = prescriptionLines.get(i).getTguide().getPosology().getFreq();
                    }

                    if(!instruc[5].isEmpty()){
                        u = FqUnit.getFqUnit(instruc[5]);
                    } else {
                        u = prescriptionLines.get(i).getTguide().getPosology().getFreqUnit();
                    }

                    prescriptionLines.set(i, new MedicalPrescriptionLine(prodID, dM, du, msg, d, f, u));
                }
            }


        } catch (Exception e){
            throw new IncorrectTakingGuidelinesException("Error");
        }
    }





    public void removeLine(ProductID prodID) throws ProductNotInPrescription {


        int count = 0;
        int prescriptionSize = prescriptionLines.size();

        for(int i = 0; i < prescriptionSize; i++) {

            if (prescriptionLines.get(i).getProdId().equals(prodID)){
                prescriptionLines.remove(i);
            } else {
                count += 1;
            }

        }

        if(count == prescriptionSize) throw new ProductNotInPrescription("NO EXISTE");


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
