package data;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;


final public class DigitalSignature {

    private final byte[] DSignature;

    public DigitalSignature(byte[] DSignature)  {
       if (DSignature == null){ throw new NullPointerException(); }
        this.DSignature = DSignature;
    }

    public byte[] getDigitalSignature(){
        return DSignature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalSignature sign = (DigitalSignature) o;
        return Arrays.equals(DSignature, sign.DSignature);
    }
    @Override
    public int hashCode() { return Arrays.hashCode(DSignature); }

    @Override
    public String toString() {
        return "DigitalSignature{" + "Digital Signature='" + new String(DSignature, StandardCharsets.UTF_8) + '\'' + '}';
    }
}
