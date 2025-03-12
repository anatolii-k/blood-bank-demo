package anatolii.k.bloodbank.blood.domain;

public class IllegalRhException extends RuntimeException {
    public IllegalRhException(String rhString) {
        super( "Illegal Rh value: %s".formatted(rhString) );
    }
}
