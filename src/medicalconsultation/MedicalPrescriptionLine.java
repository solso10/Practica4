package medicalconsultation;

import data.ProductID;

public class MedicalPrescriptionLine {

    private ProductID prodId;
    private TakingGuideline tguide;

    public MedicalPrescriptionLine(ProductID prodId, dayMoment dM, float du, String i, float d ,float f, FqUnit u) {
        this.prodId = prodId;
        this.tguide = new TakingGuideline(dM, du,i,d ,f, u);
    }

    public ProductID getProdId() {
        return prodId;
    }

    public void setProdId(ProductID prodId) { this.prodId = prodId; }

    public TakingGuideline getTguide() {
        return tguide;
    }

    public void setTguide(TakingGuideline tguide) {
        this.tguide = tguide;
    }


}
