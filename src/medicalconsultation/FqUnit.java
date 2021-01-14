package medicalconsultation;

public enum FqUnit {

    HOUR, DAY, WEEK, MONTH;


    public static FqUnit getFqUnit(String str){
        switch(str){
            case "HOUR":
                return HOUR;
            case "DAY":
                return DAY;
            case "WEEK":
                return WEEK;
            case "MONTH":
                return MONTH;
            default:
                return null;
        }
    }
}
