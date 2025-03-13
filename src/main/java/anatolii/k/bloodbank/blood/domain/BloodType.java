package anatolii.k.bloodbank.blood.domain;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BloodType {
    A_POSITIVE (ABO.A, Rh.POSITIVE),
    A_NEGATIVE (ABO.A, Rh.NEGATIVE),
    B_POSITIVE (ABO.B, Rh.POSITIVE),
    B_NEGATIVE (ABO.B, Rh.NEGATIVE),
    AB_POSITIVE (ABO.AB, Rh.POSITIVE),
    AB_NEGATIVE (ABO.AB, Rh.NEGATIVE),
    O_POSITIVE (ABO.O, Rh.POSITIVE),
    O_NEGATIVE (ABO.O, Rh.NEGATIVE);

    BloodType(ABO abo, Rh rh){
        this.abo = abo;
        this.rh  = rh;
    }

    public static BloodType fromString(String strABORh ){
        if( strABORh.isEmpty() )
        {
            return null;
        }

        Matcher matcher = bloodTypeRegEx.matcher(strABORh);
        if( matcher.find() )
        {
            try
            {
                final ABO abo = ABO.valueOf(matcher.group(1));
                final Rh  rh  = Rh.fromString(matcher.group(2));

                return Arrays.stream(values())
                        .filter( type -> type.abo == abo && type.rh == rh )
                        .findFirst()
                        .orElse(null);
            }
            catch(Throwable e){
                throw IllegalBloodTypeException.causedBy(e);
            }
        }
        throw IllegalBloodTypeException.invalidBloodTypeString(strABORh);
    }

    public ABO getAbo() {
        return abo;
    }

    public Rh getRh() {
        return rh;
    }

    @Override
    public String toString() {
        return abo.name() + ( rh == Rh.POSITIVE ? "+": "-" );
    }

    private final ABO abo;
    private final Rh rh;
    private static final Pattern bloodTypeRegEx = Pattern.compile("^([ABO]{1,2})([+-])$");
}
