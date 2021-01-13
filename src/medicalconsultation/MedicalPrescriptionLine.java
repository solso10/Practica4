package medicalconsultation;

public class MedicalPrescriptionLine {

    private String prodId;
    private TakingGuideline tguide;

    public MedicalPrescriptionLine(String prodId, dayMoment dM, float du, String i, float d ,float f, FqUnit u) {
        this.prodId = prodId;
        this.tguide = new TakingGuideline(dM, du,i,d ,f, u);
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) { this.prodId = prodId; }

    public TakingGuideline getTguide() {
        return tguide;
    }

    public void setTguide(TakingGuideline tguide) {
        this.tguide = tguide;
    }


}
