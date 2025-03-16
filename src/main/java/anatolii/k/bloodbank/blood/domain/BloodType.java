package anatolii.k.bloodbank.blood.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        BloodTypeParser parser = new BloodTypeParser();
        if( parser.parse(strABORh) )
        {
            final ABO abo = parser.getAbo();
            final Rh rh   = parser.getRh();

            return Arrays.stream(values())
                    .filter( type -> type.abo == abo && type.rh == rh )
                    .findFirst()
                    .orElse(null);
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

    private static class BloodTypeParser{

        public boolean parse(String strABORh) {

            return  parseABOFormat(strABORh)
                 || parseNumericFormat(strABORh)
                 || parseMixedFormat(strABORh);
        }

        public ABO getAbo() {  return abo; }

        public Rh getRh() { return rh; }

        private boolean parseABOFormat(String strABORh) {
            Matcher matcher = bloodTypeRegExABO.matcher(strABORh);
            if( matcher.find() ) {
                try {
                    abo = ABO.valueOf(matcher.group(1));
                    rh = Rh.fromString(matcher.group(2));
                    return true;

                } catch (Throwable e) {
                    logger.trace("Trying to parse %s as ABO format. Exception: %s".formatted(strABORh, e.getMessage()));
                }
            }
            return false;
        }

        private boolean parseNumericFormat(String strABORh) {
            Matcher matcher = bloodTypeRegExNumeric.matcher(strABORh);
            if( matcher.find() ) {
                try {
                    final ABONumericFormat aboNumeric = ABONumericFormat.valueOf(matcher.group(1));
                    abo = aboNumeric.toABOFormat();
                    rh = Rh.fromString(matcher.group(2));
                    return true;

                } catch (Throwable e) {
                    logger.trace("Trying to parse %s as ABO Numeric format. Exception: %s".formatted(strABORh, e.getMessage()));
                }
            }
            return false;
        }

        private boolean parseMixedFormat(String strABORh) {
            Matcher matcher = bloodTypeRegExMixed.matcher(strABORh);
            ABONumericFormat aboNumeric = null;
            if( matcher.find() ) {
                try {
                    abo = ABO.valueOf(matcher.group(1));
                    aboNumeric = ABONumericFormat.valueOf(matcher.group(2));
                    rh = Rh.fromString(matcher.group(3));

                } catch (Throwable e) {
                    logger.trace("Trying to parse %s as ABO Mixed format. Exception: %s".formatted(strABORh, e.getMessage()));
                    return false;
                }

                if(abo != aboNumeric.toABOFormat()){
                    final var ex = IllegalBloodTypeException.AboAndNumericFormatMismatch(strABORh, abo, aboNumeric);
                    logger.trace("Exception: %s".formatted(ex.getMessage()));
                    throw ex;
                }
                return true;
            }
            return false;
        }

        private ABO abo;
        private Rh rh;
        private static final Pattern bloodTypeRegExABO = Pattern.compile("^(A|B|AB|O)([+-])$");
        private static final Pattern bloodTypeRegExNumeric = Pattern.compile("^(I|II|III|IV)([+-])$");
        private static final Pattern bloodTypeRegExMixed = Pattern.compile("^(A|B|AB|O)\\((I|II|III|IV)\\)([+-])$");

        private static final Logger logger = LoggerFactory.getLogger(BloodTypeParser.class);
    }
}
