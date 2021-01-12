package medicalconsultation;

public class MedicalPrescriptionLine {

    private String prodId;
    private TakingGuideline tguide;

    public MedicalPrescriptionLine(String prodId, TakingGuideline tguide) {
        prodId = prodId;
        this.tguide = tguide;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        prodId = prodId;
    }

    public TakingGuideline getTguide() {
        return tguide;
    }

    public void setTguide(TakingGuideline tguide) {
        this.tguide = tguide;
    }


}
