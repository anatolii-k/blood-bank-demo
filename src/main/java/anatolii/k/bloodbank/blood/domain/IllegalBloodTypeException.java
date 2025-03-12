package anatolii.k.bloodbank.blood.domain;

public class IllegalBloodTypeException extends RuntimeException{

    public static IllegalBloodTypeException causedBy(Throwable cause){
        return new IllegalBloodTypeException("Blood Type is invalid. Reason=%s".formatted(cause.getMessage()), cause);
    }

    public static IllegalBloodTypeException invalidBloodTypeString(String bloodTypeStr){
        return new IllegalBloodTypeException("Blood Type [%s] is invalid.".formatted(bloodTypeStr));
    }

    public static IllegalBloodTypeException nullAboOrRh(){
        return new IllegalBloodTypeException("Blood Type is invalid. ABO or Rh is null");
    }

    private IllegalBloodTypeException(String msg, Throwable cause){
        super(msg, cause);
    }

    private IllegalBloodTypeException(String msg){
        super(msg);
    }

}
