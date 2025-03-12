package anatolii.k.bloodbank.blood.domain;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BloodType {

    public static BloodType from( ABO abo, Rh rh ){
        if(abo == null || rh == null){
            throw IllegalBloodTypeException.nullAboOrRh();
        }
        return new BloodType(abo, rh);
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
                ABO abo = ABO.valueOf(matcher.group(1));
                Rh  rh  = Rh.fromString(matcher.group(2));
                return new BloodType(abo, rh);
            }
            catch(Throwable e){
                throw IllegalBloodTypeException.causedBy(e);
            }
        }
        throw IllegalBloodTypeException.invalidBloodTypeString(strABORh);
    }

    private BloodType(ABO abo, Rh rh) {
        this.abo = abo;
        this.rh = rh;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BloodType bloodType = (BloodType) o;
        return abo == bloodType.abo && rh == bloodType.rh;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abo, rh);
    }

    private final ABO abo;
    private final Rh rh;
    private static final Pattern bloodTypeRegEx = Pattern.compile("^([ABO]{1,2})([+-])$");
}
