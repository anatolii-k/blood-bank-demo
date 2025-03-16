package anatolii.k.bloodbank.blood.domain;

public enum ABO {
    A,
    B,
    AB,
    O;

    public ABONumericFormat toNumericFormat(){
        return switch (this){
            case O -> ABONumericFormat.I;
            case A -> ABONumericFormat.II;
            case B -> ABONumericFormat.III;
            case AB -> ABONumericFormat.IV;
        };
    }
}
