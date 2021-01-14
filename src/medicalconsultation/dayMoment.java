package medicalconsultation;

public enum dayMoment {
    BEFOREBREAKFAST, DURINGBREAKFAST, AFTERBREAKFAST, BEFORELUNCH,
    DURINGLUNCH, AFTERLUNCH, BEFOREDINNER, DURINGDINNER, AFTERDINNER,
    BEFOEMEALS, DURINGMEALS, AFTERMEALS;

    public static dayMoment getdayMoment(String str){

        switch (str){
            case "BEFOREBREAKFAST":
                return BEFOREBREAKFAST;
            case "DURINGBREAKFAST":
                return DURINGBREAKFAST;
            case "AFTERBREAKFAST":
                return AFTERBREAKFAST;
            case "BEFORELUNCH":
                return BEFORELUNCH;
            case "DURINGLUNCH":
                return DURINGLUNCH;
            case "AFTERLUNCH":
                return AFTERLUNCH;
            case "BEFOREDINNER":
                return BEFOREDINNER;
            case "DURINGDINNER":
                return DURINGDINNER;
            case "AFTERDINNER":
                return AFTERDINNER;
            case "BEFOEMEALS":
                return BEFOEMEALS;
            case "DURINGMEALS":
                return DURINGMEALS;
            case "AFTERMEALS":
                return AFTERMEALS;
            default:
                return null;
        }

    }
}
