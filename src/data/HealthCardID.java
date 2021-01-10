package data;

import java.util.Objects;

/**
 * The personal identifying code in the National Health Service.
 */

final public class HealthCardID {

    private final String personalID;

    public HealthCardID(String code) {
        Objects.requireNonNull(code, "El código no puede ser null");
        if (code.isEmpty()){ throw new IllegalArgumentException("El código no puede ser vacío");}
        if (!isValid(code)){ throw new IllegalArgumentException("El código debe estar formado por 14 dígitos: " +
                "4 letras y 10 numeros");}
        else {
            this.personalID = code;
        }
    }

    public String getPersonalID() { return personalID; }

    public boolean isValid(String code) {
        if(code.length() != 14){
            return false;
        }
        for(int i=0; i < 4; i++){
            if((code.indexOf(i) < 'A' && code.indexOf(i)> 'Z') || (code.indexOf(i)< 'a' && code.indexOf(i)> 'z')){
                return false;
            }
        }
        for(int i=4; i < 14; i++){
            if((code.indexOf(i) < '0' && code.indexOf(i) > '9')){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID = (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    @Override
    public int hashCode() { return personalID.hashCode(); }

    @Override
    public String toString() {
        return "HealthCardID{" + "personal code='" + personalID + '\'' + '}';
    }
}