package medicalconsultation;

public class Posology {

    private float dose;
    private float freq;
    private FqUnit freqUnit;

    public Posology (float d, float f, FqUnit u) {
        this.dose = d;
        this.freq = f;
        this.freqUnit = u;
    }

    public float getDose() {
        return dose;
    }
    public float getFreq() {
        return freq;
    }

    public FqUnit getFreqUnit() {
        return freqUnit;
    }

    public void setDose(float dose) {
        this.dose = dose;
    }

    public void setFreq(float freq) {
        this.freq = freq;
    }

    public void setFreqUnit(FqUnit freqUnit) {
        this.freqUnit = freqUnit;
    }

}
