package anatolii.k.bloodbank.blood.domain;

public enum Rh {
    POSITIVE,
    NEGATIVE;

    public static Rh fromString( String RhString){
        return switch (RhString) {
            case "+" -> POSITIVE;
            case "-" -> NEGATIVE;
            case ""  -> null;
            default -> throw new IllegalRhException(RhString);
        };
    }
}
