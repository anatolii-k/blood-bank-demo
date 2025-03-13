package anatolii.k.bloodbank.blood.domain;

import java.util.*;

public class BloodTypeCompatibilityRule {

    public static boolean isCompatible(BloodType recipient, BloodType donor ){
        if( recipient == null || donor == null ){
            throw new NullPointerException();
        }
        Set<BloodType> compatibleDonors = compatibleBloodTypes.get(recipient);
        return compatibleDonors.contains(donor);
    }

    private static final EnumMap<BloodType, EnumSet<BloodType>> compatibleBloodTypes = new EnumMap<>(BloodType.class);

    static{
        // A+
        compatibleBloodTypes.put( BloodType.A_POSITIVE,
                EnumSet.of( BloodType.A_POSITIVE,
                        BloodType.A_NEGATIVE,
                        BloodType.O_POSITIVE,
                        BloodType.O_NEGATIVE
                ));

        // A-
        compatibleBloodTypes.put( BloodType.A_NEGATIVE,
                EnumSet.of( BloodType.A_NEGATIVE,
                        BloodType.O_NEGATIVE
                ));

        // B+
        compatibleBloodTypes.put( BloodType.B_POSITIVE,
                EnumSet.of( BloodType.B_POSITIVE,
                        BloodType.B_NEGATIVE,
                        BloodType.O_POSITIVE,
                        BloodType.O_NEGATIVE
                ));

        // B-
        compatibleBloodTypes.put( BloodType.B_NEGATIVE,
                EnumSet.of( BloodType.B_NEGATIVE,
                        BloodType.O_NEGATIVE
                ));

        // AB+
        compatibleBloodTypes.put( BloodType.AB_POSITIVE,
                EnumSet.of( BloodType.A_POSITIVE,
                        BloodType.A_NEGATIVE,
                        BloodType.B_POSITIVE,
                        BloodType.B_NEGATIVE,
                        BloodType.AB_POSITIVE,
                        BloodType.AB_NEGATIVE,
                        BloodType.O_POSITIVE,
                        BloodType.O_NEGATIVE
                ));

        // AB-
        compatibleBloodTypes.put( BloodType.AB_NEGATIVE,
                EnumSet.of( BloodType.A_NEGATIVE,
                        BloodType.B_NEGATIVE,
                        BloodType.AB_NEGATIVE,
                        BloodType.O_NEGATIVE
                ));

        // O+
        compatibleBloodTypes.put( BloodType.O_POSITIVE,
                EnumSet.of( BloodType.O_POSITIVE,
                        BloodType.O_NEGATIVE
                ));

        // O-
        compatibleBloodTypes.put( BloodType.O_NEGATIVE,
                EnumSet.of( BloodType.O_NEGATIVE ));
    }

}
